package autoscout24.reporting_api.utils;

import autoscout24.reporting_api.model.Contact;
import autoscout24.reporting_api.model.Listing;
import autoscout24.reporting_api.repository.ContactRepository;
import autoscout24.reporting_api.repository.ListingRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Component
public class DatabaseLoader implements ApplicationRunner {

    private final ContactRepository contactRepository;
    private final ListingRepository listingRepository;

    public DatabaseLoader(ContactRepository contactRepository, ListingRepository listingRepository) {
        this.contactRepository = contactRepository;
        this.listingRepository = listingRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        loadListingsToDatabase();
        loadContactsToDatabase();
    }

    private void loadListingsToDatabase() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/listings.csv"))) {
            String record;
            while ((record = br.readLine()) != null) {
                String[] recordValues = record.split(",");
                if (!recordValues[0].equals("\"id\"")) {
                    Listing listing = mapCsvRecordToListing(recordValues);
                    listingRepository.save(listing);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("CSV file is not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("CSV file could not be read.");
            e.printStackTrace();
        }
    }

    private Listing mapCsvRecordToListing(String[] recordValues) {

        Integer id = Integer.parseInt(recordValues[0]);
        String make = recordValues[1].substring(1, recordValues[1].length() - 1);
        Integer price = Integer.parseInt(recordValues[2]);
        Integer mileage = Integer.parseInt(recordValues[3]);

        String sellerType = "other";
        if (recordValues[4].equals("\"private\"")) {
            sellerType = "private";
        } else if (recordValues[4].equals("\"dealer\"")) {
            sellerType = "dealer";
        }

        return new Listing(id, make, price, mileage, sellerType);
    }

    private void loadContactsToDatabase() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/contacts.csv"))) {
            String record;
            while ((record = br.readLine()) != null) {
                String[] recordValues = record.split(",");
                if (!recordValues[0].equals("\"listing_id\"")) {
                    Contact contact = mapCsvRecordToContact(recordValues);
                    contactRepository.save(contact);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("CSV file is not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("CSV file could not be read.");
            e.printStackTrace();
        }
    }

    private Contact mapCsvRecordToContact(String[] recordValues) {
        Integer listingId = Integer.parseInt(recordValues[0]);

        long epochDate = Long.parseLong(recordValues[1]);
        Instant dateInstant = Instant.ofEpochMilli(epochDate);
        LocalDateTime dateTime = LocalDateTime.ofInstant(dateInstant, ZoneId.systemDefault());

        return new Contact(listingId, dateTime);
    }
}
