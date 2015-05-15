# sqlite-helper

[![Build Status](https://travis-ci.org/ichigotake/android-sqlite-helper.svg)](https://travis-ci.org/ichigotake/android-sqlite-helper) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.ichigotake/android-sqlite-helper/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.ichigotake/android-sqlite-helper) 
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/ichigotake/android-sqlite-helper?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

sqlite-wrapper - [DRAFT] very simple SQLite helpers for AndroidSDK

## WARNING

Currently status is making a draft.

**THIS IS A DEVELOPMENT RELEASE. API MAY CHANGE WITHOUT NOTICE.**

## Policy

This is very simple `SQLiteOpenHelper` wrapper for lightweight application.

Don't use reflection, annotation processor, no proguard configuration.

If you may need rich interfaces, we recommends to use other library.

## Todo

- [x] ~~Project naming (Rough naming now)~~
- [x] ~~Implement create table configuration~~
- [x] ~~Implement very simple `SELECT` query builder~~
- [x] ~~Implement DDL query builder~~
- [x] ~~Implement migration~~
- [x] ~~Introduce CI (circleci or travis-ci or wrecker?)~~
- [ ] Writing document
- [ ] Create sample application
- [x] ~~Shipt to MavenCentral~~

## For contributors

- Development with AndroidStudio 1.0.0 or higher
- Run unit test with `testDebug` task
