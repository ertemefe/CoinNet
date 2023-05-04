<a name="br1"></a>README

Project description:

In this project, the aim was develop a CoinGecko API based application layerprotocol, based on the client/server model. CoinNet server uses TCP connectionto interact with the clients. Only the CoinNet server interacts with theCoinGecko API web server and provides the CoinNet client with theinformation required.

OVERVIEW

In the project, using the command line, the client get responses from the server whichconnects to the COINGECKO API and retrieves then parses that data in classes using theGSON library. Then this parsed data is passed to the client. The protocol that was establishedis that any given client entry (query) cannot be over than 100 bytes. If so the connectionterminates. The following snapshots will be the given proofs. Also the application ends when“QUIT” is typed.
