# sqlite-helper [![Build Status](https://travis-ci.org/ichigotake/android-sqlite-helper.svg)](https://travis-ci.org/ichigotake/android-sqlite-helper)

sqlite-wrapper - [DRAFT] very simple SQLite helpers for AndroidSDK

## WARNING

Currently status is making a draft.

**THIS IS A DEVELOPMENT RELEASE. API MAY CHANGE WITHOUT NOTICE.**

## Policy

This is very simple `SQLiteOpenHelper` wrapper for lightweight application.

Don't use reflection, annotation processor, and other magics.

If you may need rich interfaces, I recommends to use other library.

## Todo

- [ ] Project naming (Rough naming now)
- [x] Implement create table configuration
- [x] Implement very simple `SELECT` query builder
- [x] Implement DDL query builder
- [ ] TBD: Implement migration helper
- [x] Introduce CI (circleci or travis-ci or wrecker?)
- [ ] Writing document
- [ ] Create sample application
- [ ] Shipt to jcenter

## For contributors

- Development with AndroidStudio 1.0.0 or higher
- Run unit test with `testDebug` task
