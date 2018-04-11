# Whale

### Made by Sam and Ben Whale at BrumHack 2017

Whale is a Java application which uses the Spotify Song recommendation API to play 30 second preview clips based off financial data in a csv.

The initial song seed is defined in the program, and each consecutive song is fed into the spotify API along with parameters for the next song.

These parameters are:  
  Energy: How upbeat or relaxed the song is - Based off the recent financial activity levels  
  Valence: How happy the song is - Based off how high or low the stock is  
