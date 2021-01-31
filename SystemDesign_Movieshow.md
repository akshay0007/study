Book my show---

Capcity estimation--

DB overview -- 50 bytes

	(IDs, NumberOfSeats, ShowID, MovieID,SeatNumbers, SeatStatus, Timestamp,etc)
	
calcuations--

		500 cities * 10 cinemas * 2000 seats * 2 shows * (50+50) bytes = 2GB / day 


System API--
		
Db structure-- 

	SearchMovies(api_dev_key, keyword, city, lat_long, radius, start_datetime,end_datetime, postal_code,includeSpellcheck, results_per_page, sorting_order)
	
    eg-- 
    [{
     "MovieID": 1,
     "ShowID": 2,
     "Title": "Cars 2",
     "Description": "About cars",
     "Duration": 120,
     "Genre": "Animation",
     "Language": "English",
     "ReleaseDate": "8th Oct. 2014",
     "Country": USA,
     "StartTime": "16:30",
     "EndTime": "18:30",
     "Seats":
     [
     {
     "Type": "Regular"
     "Price": 14.99
     "Status: "Full"
     },
     {
     "Type": "Premium"
     "Price": 24.99
     "Status: "Almost Full"
     }
     ]
     }]



ReserveSeats(api_dev_key, session_id, movie_id, show_id, seats_to_reserve[])



Database Design---
	
  1. Each City can have multiple Cinemas.
  2. Each Cinema will have multiple halls.
  3. Each Movie will have many Shows and each Show will have multiple Bookings.
  4. A user can have multiple bookings. 


High level arch-

At a high-level, our web servers will manage users’ sessions and application servers will handle all the
ticket management, storing data in the databases as well as working with the cache servers to process
reservations.


---
  How would the server keep track of all the active reservation that haven’t been booked yet? And
  how would the server keep track of all the waiting customers?


    ActiveReservationService
    WaitingUserService
	
Structure--	ActiveReservationService

    HashTable<‘ShowID’,LinkedHashMap<‘BookingID’,'‘Timestamp’'>>

  ‘Status’--{‘Reserved,‘Booked,‘Expired}	

  and remove the reservation record from
  the Linked HashMap of the relevant show. When the reservation is expired,


Structure-- WaitingUsersService

  LinkedHashMap<ShowID,Map<‘UserIDs’,wait-start-time>>

  Clients can use Long Polling

Concurrency--How to handle concurrency, such that no two users are able to book same seat.
	
Transactions in SQL databases to avoid any clashes.

Locks--
		
code:

    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    BEGIN TRANSACTION;

    -- Suppose we intend to reserve three seats (IDs: 54, 55, 56) for ShowID=99
     Select * From Show_Seat where ShowID=99 && ShowSeatID in (54, 55, 56) &&
    Status=0 -- free
     -- if the number of rows returned by the above statement is three, we can
    update to
     -- return success otherwise return failure to the user.
     update Show_Seat ...
     update Booking ..
     COMMIT TRANSACTION;
	 

‘Serializable’ is the highest isolation level and guarantees safety from Dirty, Nonrepeatable, and
Phantoms reads. One thing to note here; within a transaction, if we read rows, we get a write lock on
them so that they can’t be updated by anyone else.


Data Partitioning--
		
		If we partition by ‘MovieID’ -- it crate lots of the load in severs.
		partition based on ShowID; this way, the load gets distributed among different servers.
