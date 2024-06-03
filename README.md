# Ingryd-Airways

Ingryd Airways Flight Reservation System 
This project implements a flight reservation system for Ingryd Airways, allowing customers to book flights and manage reservations, and admins to manage flights and schedules.

Features:

Customer Functions:
Search for available flights by origin, destination, and date (using a simulated Airport API).
Book flights and reserve tickets for scheduled flights.
View flight schedules, including departure times, arrival times, available seats, and other details.
Make reservations for multiple passengers under one itinerary.
Cancel reservations within a specified timeframe for a refund (with a penalty after the deadline).
View reservation details using their name, ticket number, or phone number.
(Optional) Select preferred seats during booking (currently assigned automatically).
(Optional) Choose additional services like extra luggage or meals during booking.
Admin Functions:
Manage aircraft (add, cancel).
Schedule flights, including assigning aircraft.
Update flight schedules (add/remove flights, modify schedules, reassign aircraft).
Notify stakeholders (customers and other admins) about flight schedule changes or cancellations.
System Functions:
Process payments (currently dummy, future integration planned).
Send notifications to customers regarding reservations, modifications, or flight updates from admins.
Manage user accounts for admins, registered users, and guests (login, registration).
Technology Stack (Placeholder - Replace with your actual choices):

Programming Language: Java (For the Back-end).
Framework: Spring Boot.
Database:MySQL
Project Setup Instructions:

Clone this repository: git clone https://github.com/Legend-re/Ingryd-Airways.git
Install dependencies: Allow your Maven dependency manager to resolve dependencies.
Configure database connection: Follow instructions in the to set up your database connection details, locate your IDE edit configurations for the Ingryd-Airways and under your modify or edit environment variables settings add your personal database login password and save the changes, that way it with will correspond to the settings in the application.properties file.
Run the application: You run the application from the main (IngrydAirwaysApplication) class.

Getting Started:
Review the codebase to understand the implementation.
Explore the functionalities from both customer and admin perspectives.
Feel free to modify and extend the functionalities as needed.

Documentation:
This READEME provides a high-level overview.

//ToDO [folder containing detailed documentation]:
Refer to the folder for detailed API documentation, class diagrams, and user flow diagrams.

Note:
The Airport API integration and real payment processing are currently simulated for development purposes.
This project provides a solid foundation for a flight reservation system. 
You can further enhance it by adding features like:

Real-time flight search with an external API.
Secure payment processing integration.
Advanced seat selection options.
Integration with email or SMS gateways for notifications.
User accounts with profile management.
