# EncloSys
Inspired by [Shubaruuu's](https://github.com/SHUBARUUU) Github repo [Z-inventory](https://github.com/SHUBARUUU/Z-Inventory). A 2nd year 1st sem prelim project for
the course Data Structures and Algorithms

## Table of Contents
- [Dedication](#enclosys)
- [Description](#description)
- [Limitations](#limitations)
- [Sample Console Outputs](#sample-console-outputs)
- [Disclaimer](#disclaimer)
  
## Description
Each groups were tasked to create a simple console-based zoo inventory tracking system that can store up to 50 animals.
The program should allow zookeepers to:

- Add new animal records (name, species, age, enclosure number 0–4).
- View all registered animals. 
- Search animals by name to see details. 
- Count animals by species. 
- Display animals in a specific enclosure.

This program only uses a single class file. To separate concerns of methods, section three-line headers
were set in place

## Limitations
Advanced data structures like objects, lists, or collections
should be avoided. Only beginner java concepts

## Sample Console Outputs

```
Welcome to Z-Inventory System!
------------------------------
[1] Add a new animal
[2] View all animals
[3] Search animal by name
[4] Count animals by species
[5] View animals by enclosure
[0] Exit

Enter option: 1

[SYSTEM] Preparing to add a new animal... 
Enter animal name: Kanna Rheia
Enter animal age: Legal

[ERROR] Invalid input. Please try again.
Enter : 8
Enter animal specie: Vtuber
Enter animal enclosure num: 0

[SYSTEM] Kanna Rheia added successfully!

```

```
Welcome to Z-Inventory System!
------------------------------
[1] Add a new animal
[2] View all animals
[3] Search animal by name
[4] Count animals by species
[5] View animals by enclosure
[0] Exit

Enter option: 2

[SYSTEM] Preparing to show all animal details... 
Name: Kanna Rheia              | Specie: Vtuber           | Age: 8   | Enclosure: 0
Name: Hatsune Miku             | Specie: Vocaloid         | Age: 16  | Enclosure: 0
Name: Kagamine Rin             | Specie: Vocaloid         | Age: 10  | Enclosure: 1
```
## Disclaimer
EncloSys is a practice project developed for a university project.
While functional, the codebase may not reflect best practices for a production-ready tool.
