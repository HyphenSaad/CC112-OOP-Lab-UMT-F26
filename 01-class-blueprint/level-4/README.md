![Level 4](https://img.shields.io/badge/level-4_hard-ef4444?style=for-the-badge)
![Problems](https://img.shields.io/badge/problems-5-8b5cf6?style=for-the-badge)

# Level 4 — Mini Systems

**Goal:** combine everything into small, realistic classes.

Rules from Levels 1–3 still apply. What is new: 3–5 attributes, multiple rules, and at least one method whose behaviour depends on the object's current state.

## The five problems

| # | Problem | Attributes | Special methods |
|---|---|---|---|
| 16 | [InventoryItem](problem-16-inventory-item/) | id, name, quantity, unitPrice, reorderLevel | `reorder()`, `isLowStock()`, `totalValue()` |
| 17 | [AtmMachine](problem-17-atm-machine/) | pin, balance, isLocked, failedAttempts | `authenticate()`, `deposit()`, `withdraw()`, `getStatus()` |
| 18 | [WaterBottle](problem-18-water-bottle/) | capacityMl, currentMl | `fill()`, `drink()`, `isFull()`, `isEmpty()`, `remaining()` |
| 19 | [GymMember](problem-19-gym-member/) | name, monthsActive, monthlyFee, totalPaid | `renew()`, `cancel()`, `isActive()`, `monthlyCost()` |
| 20 | [RestaurantBill](problem-20-restaurant-bill/) | subtotal, taxPercent, servicePercent | `tax()`, `service()`, `total()` |

## Run any problem

```bash
cd ..
./run.ps1 -l 1 -v 4 -p 16 -o run,test
```
