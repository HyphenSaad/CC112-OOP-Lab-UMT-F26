![Level 3](https://img.shields.io/badge/level-3_medium-f97316?style=for-the-badge)
![Problems](https://img.shields.io/badge/problems-5-8b5cf6?style=for-the-badge)

# Level 3 — Branching on State

**Goal:** methods whose behaviour depends on the object's current state.

Rules from Levels 1–2 still apply — private attributes, `Check`-validated setters with silent reject, `isValid()`, no exceptions. What is new: a method **reads its own state** to decide whether an action is allowed.

## The five problems

| # | Problem | Attributes | Special methods |
|---|---|---|---|
| 11 | [ShoppingCartItem](problem-11-shopping-cart-item/) | name, price, quantity, discountPercent | `lineTotal()`, `isBulkOrder()`, `applyDiscount()` |
| 12 | [MovieTicket](problem-12-movie-ticket/) | movieName, seatNumber, price, isBooked | `book()`, `cancel()`, `getStatus()` |
| 13 | [Player](problem-13-player/) | name, health, maxHealth | `takeDamage()`, `heal()`, `isAlive()` |
| 14 | [ParkingSlot](problem-14-parking-slot/) | slotNumber, isOccupied, vehiclePlate | `park()`, `unpark()`, `isAvailable()` |
| 15 | [EmailValidator](problem-15-email-validator/) | email | `isValidEmail()`, `normalize()`, `domain()` |

## Run any problem

```bash
cd ..
./run.ps1 -l 1 -v 3 -p 11 -o run,test
```
