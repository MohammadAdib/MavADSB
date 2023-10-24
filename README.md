# MavADSB
Loads ADSB data into Mission Planner or QGC without a need for an ADSB receiver. This data can be forwarded to the UAS (via telemetry link) for collision avoidance without the need for an onboard ADSB-in receiver or any additional hardware.

<img width="1970" alt="image" src="https://github.com/MohammadAdib/MavADSB/assets/1324144/51630334-e4dc-4995-af77-3b710f7a382f">

### How to use
Get the Jar file in out/artifacts/MavADSB_jar/ and type this into the terminal:

```
java -jar MavADSB.jar lat lon
```

Replace ```lat``` and ```lon``` with your location, and it will display all ADSB data within 250nm

### API & Docs
ADSB-One API: https://github.com/ADSB-One/api/blob/main/README.md

SBS-1 Info: http://woodair.net/SBS/Article/Barebones42_Socket_Data.htm
