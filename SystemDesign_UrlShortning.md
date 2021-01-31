Create url shortning-- 

  https://dzone.com/articles/url-shortener-detailed-explanation#:~:text=URL%20Conversion&text=The%20most%20important%20thing%20in,MD5%20or%20SHA%2D2).
  
  Url Shortning--
Estimation of redirection and ratio-- 100:1

storage capcity--

  nos. of links storage per hour -- and calculate this to per month basic


Redirection of request-

  Hot url storage inside cache--


Total estimation--
New URLs 200/s
URL redirections 20K/s
Incoming data 100KB/s
Outgoing data 10MB/s
Storage for 5 years 15TB
Memory for cache 170GB


Securities-- 
we can limit users via their api_dev_key ,so that malicious retrivel and redirection have some quoate

Db Design--No sql is better choice

  Url mapping table-
  User table--


Solutions- 
  Encoding url--MD5 , base64 
  base 64 with 8 bit and 6 bit creating duplicate records -- way around can append usr details


  Genrating keys offline-- Key Generation Service (KGS)--> pre genrated 8 bit keys
  --=but  creating problem in concurrent system


Two db -- 
1- for all used key 
2- not used keys

it must synchronize(or get a lock on) the data structure holding the keys before removing keys from


  KGS -- primary server failure 


Data Partitioning and Replication---


Range based partitioning--

	stored url on the basisc of first chareacter and have 26 different partition.. but wrong apporach due to inconssisent storage
	
Hash-Based Partitioning	---

	paritition of hash of url and having of modulao of 256 bits
	
	
8. Cache--We can start with 20% of daily traffic and, based on clients’ usage
pattern, we can adjust how many cache servers we need.


Cache eviction policy-- LRU

Load Balancer (LB)

	1. Between Clients and Application servers
	2. Between Application Servers and database servers
	3. Between Application Servers and Cache servers 
	
Purging DB--

  A separate Cleanup service can run periodically to remove expired links from our storage and
  cache. This service should be very lightweight and can be scheduled to run only when the user
  traffic is expected to be low. 	


Permission--

 permission level (public/private) with each URL in the database



	





