# Architecture

We use a 3-tier architecture:
*  [Logic](#logic)
*  [Persistence](#persistence)
*  [Presentation](#presentation)

Other
*  [Application](#application)
*  [Objects](#objects)
*  [Resources](#resources)
*  [Layout](#layout)


<a name="#application"></a>
## Application

```
Main.java - sets the database name
Services.java` - creates and initializes the persistence databases with info
```

<a name="#logic"></a>
## Logic

*Accessors*
```
AccessFlights.java - contains all the public methods to retrieve all the information about flights
AccessItems.java - contains all the public methods to retrieve all the information about items
AccessPlanets.java - contains all the public methods to retrieve all the information about planets
```
*Other*
```
CalculatePrice.java - calculates the price for any given flight, this is a placeholder for now
CheckCard.java - checks the format of credit card submitted and ensures it is of proper format
CheckDiscountCode.java - checks to see if the code entered is valid and applies the discount if it is
AnalyseDates.java - manages conflicting dates, calculates travel duration
DistanceHandler - calculates distance between different locations
```
*Exceptions*
```
CardException.java
CouponJavaException.java
```

<a name="#objects"></a>
## Objects

```
Flight.java - contains all the information for a flight
Location.java - contains all the information for a location (planet)
Item.java - contains all the information for an item
```

<a name="#persistnece"></a>
## Persistence

*Interfaces*
```
FlightPersistence.java - holds flights that are going to depart
ItemPersistence.java - holds item info
PlanetPersistence.java - holds planets info
```
*HSQLDB*
```
FlightPersistenceHSQLDB.java - makes the flight database from the sql database
ItemPersistenceHSQLDB.java - makes the item database from the sql database
PlanetPersistenceHSQLDB.java - makes the planet/location databse from the sql database
```

<a name="#presentation"></a>
## Presentation

```
MainActivity.java - creates 3 buttons that lead to other activites, including seeing current flight status and booking a flight
```

### Book flight
*Browse flights*
```
SelectOrigin.java - gets and then contains the information for the choose an origin view
SelectDestination.java - gets and then contains the information for the choose a destination view
BrowseFlightsCalendar.java
BrowseFlightsList.java
FlightDetailActivity.java - shows details about the currently selected flight
PurchaseTicket.java - shows the total price of the ticket and check the format of the Visa card
```
*Travel class*
```
SelectTravelClass.java - gets the information about a travel class
SelectDailyExpenses.java - creates and sets all the additional options you can choose once a flight is selected
```
```
PurchaseTicket.java
ReviewBooking.java
```
### Browse Planets
```
PlanetList.java - contains a list of planets you can go to, with a picture of them and a small piece of information in a custom listview
Info.java - gets called when a flight or planet is selected from flightstatus or planetlist respectively
```
### Track Flights
```
FlightStatusList.java - shows the status of current flights in a custom listview
StatusDetail.java
```
### Util
*List adapters*
```
FlightListAdapter.java
PlanetListAdapter.java
```
```
DateParser.java - makes a string from a date
Messages.java - simple class for dealing with errors and warnings
```

<a name="#resources"></a>
## Resources

### Drawable
This folder contains vector images for our app, including pictures of earth, venus, etc.
All images are free and available here: https://www.flaticon.com/

<a name="#layout"></a>
### Layout
```
activity_main.xml - describes the layout and image on the buttons on the main activity
```
*Booking*
```
activity_book_select_planet.xml - describes the format for the choose an origin or destination view activity
activity_book_browse_list.xml - describes the format for the booking on the list view activity
activity_book_browse_calendar.xml - describes  format for the booking on the calender view activity
activity_book_flight_detail.xml - contains the layout to be used when viewing details of a flight
activity_book_select_travel_class.xml - describes the format for the choose a travel class
activity_book_select_items.xml - describes the complex view for additional options (meal plan and virtual reality options)
activity_book_review.xml - describes the layout when reviewing a booking
activity_book_purchase.xml - describes the layout you see when purchasing a ticket
```
*Detailed*
```
activity_flight_status_detail.xml - a detailed description of an ongoing flight
activity_planet_info.xml - a detailed description of a planet
```
*Other*
```
activity_listview.xml - contains all the information on text size, color, border sizes for the the custom listview
list.xml - describes the format for the list of possible destination planets or flights
```