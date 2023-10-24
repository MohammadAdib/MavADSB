# MavADSB
Loads ADSB data into Mission Planner or QGC without a need for an ADSB receiver. This data can be forwarded to the UAS (via telemetry link) for collision avoidance without the need for an onboard ADSB-in receiver or any additional hardware. Works on Mac, Linux or Windows.

<img width="1970" alt="image" src="https://github.com/MohammadAdib/MavADSB/assets/1324144/97b7e787-d24d-4bae-881f-30ecf718c9c8">

<img width="1970" alt="image" src="https://github.com/MohammadAdib/MavADSB/assets/1324144/51630334-e4dc-4995-af77-3b710f7a382f">

### Basic usage
Make sure to have Java installed. Get the Jar file in out/artifacts/MavADSB_jar/ and type this into the terminal:

```
java -jar MavADSB.jar lat lon
```

Replace ```lat``` and ```lon``` with your location, and it will display all ADSB data within 250nm


### Advanced usage
```
java -jar MavADSB.jar lat lon radius interval
```

Replace ```radius``` with the polling radius around  ```lat,lon``` (up to 250) in nautical miles. Optional

Replace ```interval``` with the polling interval in milliseconds (minimum 1000) in milliseconds. Optional

### Mission Planner integration
After running the Jar file, please open Mission Planner and go to config tab -> Planner and look for the "Adsb" checkbox. Enable this and restart Mission Planner

<img width="550" alt="MP" src="https://github.com/MohammadAdib/MavADSB/assets/1324144/27f5d3e9-3728-4630-b12b-26d234c433ba">

### QGroundControl integration
After running the Jar file, please open QGC and click the Q on the top-left. Go into application settings and scroll to the bottom. Enable ADSB and restart QGC

<img width="550" alt="image" src="https://github.com/MohammadAdib/MavADSB/assets/1324144/6b1bfb0f-f257-4f2a-b743-53be2868f460">

### API & Docs
Install Java: https://www.java.com/en/download/

ADSB-One API: https://github.com/ADSB-One/api/blob/main/README.md

SBS-1 Info: http://woodair.net/SBS/Article/Barebones42_Socket_Data.htm

Server runs on localhost:30003

### Traditional methods
GCS connected USB dongle: https://uavionix.com/products/pingusb/

UAS connected ADSB rx: https://uavionix.com/products/pingrx-pro/

It is still recommended to use the above for least latency. However paired with this software the range is greatly extended. Enjoy :)
