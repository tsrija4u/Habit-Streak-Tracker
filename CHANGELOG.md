# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## 2026.11.07

### Added

- Designed kernel implementation for HabitStreakTracker component
- Designed abstract class for HabitStreakTracker component
- Designed kernel and enhanced interfaces for HabitStreakTracker component

### Updated

- Changed design to represent HabitStreakTracker as a `Set<Integer>` of
  logged days
- Changed design to implement `equals`, `hashCode`, and `toString` in the
  secondary class using only kernel methods
- Changed design to inherit `clear` and `transferFrom` from `Standard`
  instead of declaring them in the component
- Changed design to add contracts (`@requires`, `@ensures`, `@updates`) to
  every method

## 2026.11.01

### Added

- Designed abstract class for HabitStreakTracker component

### Updated

- Changed design to implement `equals`, `hashCode`, and `toString` in the
  secondary class using only kernel methods
- Changed `toString` to list days in increasing order, e.g., `{1, 2, 3}`

## 2026.11.01

### Added

- Designed kernel and enhanced interfaces for HabitStreakTracker component

### Updated

- Changed design to inherit `clear` and `transferFrom` from `Standard`
  instead of declaring them in the component
- Changed design to add contracts (`@requires`, `@ensures`, `@updates`) to
  every method

## 2026.10.31

### Added

- Designed a proof of concept for HabitStreakTracker component

### Updated

- Changed design to include `clear` and `transferFrom` so secondary methods
  can restore `this` after using `removeAny`
- Changed design to include a `toString` method that lists the logged days

## 2026.10.28

### Added

- Designed a HabitStreakTracker component
- Designed a HabitSequence component
- Designed a HabitLog component
