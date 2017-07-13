# Project Structure

There are three different anonymisers that have been created to pseudo-anonymise three different types of cancer data. These anonymisers are:
1. **CWTAnonymiser.java** (for CWT data) and is stored in *peach.anonymiser.cwt*
2. **COSDAnonymiser.java** (for COSD data) and is stored in *peach.anonymiser.cosd*
3. **SACTAnonymiser.java** (for SACT data) and is stored in *peach.anonymiser.sact*

The peach.anonymiser package consists of:
* **BaseAnonymiser.java** a super class which all the other anonymiser's inherit. It deals with reading CSV files, outputting CSV files and hashing data.
* **Anonymiser.java** an interface consisting of a single method anonymise() which every anonymiser will have. This function should consist of the logic to pseudo-anonymise data.

# How The Anonymisers Work
The main principle of these anonymisers are to hash sensitive and identifying data so that the original data is disguised, but each patient or organisation can still be 
distinguished anonymously.

## CWT Anonymiser
The following attributes are sensitive, and have therefore been hashed:
* NHS Number
* Patient Pathway Identifier (PPI)
* Organisation Code  (PPI Identifier)
* Site Code (of Provider Consultant upgrade)
* Site Code (of Provider First Seen)
* Site Code (of Provider Decision To Treat Cancer)
* Site Code (of Treatment Start Date Cancer)

All other attributes are insensitive and have not been modified.

## COSD Anonymiser
The following attributes are sensitive, and have therefore been hashed:
* NHS Number
* Local Patient Identifier
* Person Family Name
* Person Given Name
* Person Family Name (At Birth)
* Patient Usual Address (At Diagnosis)
* Organisation Code (Code of Provider)

The folllowing sensitive attributes have been modified differently:
* **Patient Birth Date** - Only the year of birth is shown, so a rough age of the patient can be generated
* **Postcode of Usual Address (At Diagnosis)** - Only the first half of the postcode is shown, which gives a high level overview of the location