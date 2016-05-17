#Band Tracker

A web app that lets ypu track your favoirte bands by adding bands and the locations they have played at.

##By Peter Beach

##PSQL Set-Up:

CREATE DATABASE band_tracker;
\c band_tracker
CREATE TABLE bands (id serial PRIMARY KEY, name varchar);
CREATE TABLE venues (id serial PRIMARY KEY, name varchar);
CREATE TABLE bands_venues (id serial PRIMARY KEY, band_id int, venue_id int)
CREATE DATABASE band_tracker_test WITH template band_tracker;

##Set-Up:

Clone the repository to your desktop and run gradle, then open your browser and go to localhost:4567/
