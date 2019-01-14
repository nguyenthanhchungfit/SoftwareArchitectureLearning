Các dependencies
1. Cài đặt MongoDB

2. Cài đặt Zookeeper và kafka
https://devops.profitbricks.com/tutorials/install-and-configure-apache-kafka-on-ubuntu-1604-1/

3. cd vào thư mục source/WebsiteNgheNhacTrucTuyenV2/script
./mongo_start.cmd
./kafka_start.cmd
./elastic_search_start.cmd

4. Chạy lần lượt các file thực thi trong danh sách project
CrawlerWorker/mp3.crawler.listener.LookupSongListener.java
CrawlerWorker/mp3.crawler.listener.ResoucesCrawler.java
Mp3IdentifierSystem/mp3.identification.app.MainApp.java
Mp3ThriftServer/mp3.me.thrift.app.Mp3ThriftMainApp.java
Mp3WebServer/mp3.me.app.Mp3MainApp.java
