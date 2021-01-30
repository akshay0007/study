Facbook design--
Basic requirement--

  - one to one chat server
  - online and offline status types of things
  - basic persistent storage


capacity estimaiton--
storage capacity- per day according to per minutes chats by single user


      user1 and user2
       \		   /	
         chat server
         |
          storage


Messaging design--
Pull model-- client keep request to chat server for notifing whether a new message is coming
Push model-- server notified to user regarding new message

we have need to connect chat server (open connection for notification)--

    Long polling (http)
    Web socket		


Type of map--

    Map<UserId,Connection> mapConnections


Message ordering--

1-Messageing on storage on the basis of Sequence Number (maintain inside db)

2-time stamp


Storage--

    Required Hbase type of storage haveing capcity to store multiple values corresponding to pariticular key

Whenever a message comes in picture a asynch reuqest go to dbserver for storing messages. so that blocking not happend for any user

Whenever a new message arrives, the chat server will push it to the receiving user on
the long poll request


Data Partitioning--
Sharding on the basis of UserId

    No of shared -1000 

    for any user hash(userid%1000


Replicaiton Algo--

    Reed-Solomon encoding to distribute and replicate it.


GroupChatID--

    separate table partitioned


