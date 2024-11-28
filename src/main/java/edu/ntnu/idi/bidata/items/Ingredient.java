package edu.ntnu.idi.bidata.items;

import edu.ntnu.idi.bidata.common.Unit;

import java.time.LocalDate;

/**
 * <p>The Ingredient class represents an ingredient entity that can be stored in a FoodStorage.</p>
 *
 * <p>Each Ingredient object holds key information about the ingredient, including its
 * <b>name</b>, <b>description</b>, <b>quantity</b>, <b>unit</b>, <b>price</b>, and
 * <b>expiration date</b>.</p>
 *
 * <p>Instances of Ingredient can be managed within a FoodStorage object to maintain
 * an organized collection of ingredients.</p>
 *
 * @author Johannes Nupen Theigen
 * @version 0.1.6
 * @since 11.28.2024
 */

public class Ingredient {
  private String name;
  private double quantity;
  private Unit unit;
  private double price;
  private LocalDate expirationDate;

  /**
   * <p>Creates a new instance of Ingredient that can be
   * stored in a FoodStorage.</p>

   * @param name the name an ingredient
   * @param quantity the quantity of an ingredient
   * @param unit the unit of an ingredient (gram, litre, etc.)
   * @param price the price of an ingredient. (price in Norwegian kr)
   * @param expirationDate the expiration date of an ingredient.
   */
  public Ingredient(String name,
                    double quantity, Unit unit, double price, LocalDate expirationDate) {
    setName(name);
    setQuantity(quantity);
    setUnit(unit);
    setPrice(price);
    setExpirationDate(expirationDate);
  }

  /**
   * <p>
   * Creates a new instance of Ingredient that is used when adding
   * a new ingredient to a recipe which only requires the name, quantity
   * and unit.
   * </p>

   * @param quantity the quantity of an ingredient
   * @param unit the unit of an ingredient (gram, liter, etc.)
   */
  public Ingredient(String name, Double quantity, Unit unit) {
    setName(name);
    setQuantity(quantity);
    setUnit(unit);
  }

  /**
   * Retrieves the name of an ingredient.
   *
   * @return the name of this ingredient
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the ingredient.
   *
   * @param name the name of the ingredient. The name cannot be null or empty.
   * @throws IllegalArgumentException if the name is null or empty.
   */
  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The ingredient name cannot be null or empty");
    }
    this.name = name;
  }

  /**
   * <p>Retrieves the quantity of an ingredient.</p>

   * @return the quantity of an ingredient
   */
  public double getQuantity() {
    return quantity;
  }

  /**
   * <p>Sets the quantity of an ingredient.</p>

   * @param quantity the quantity cannot be less than zero.
   * @throws IllegalArgumentException If the quantity is less than zero.
   */
  public void setQuantity(double quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("The amount cannot be 0 or less.");
    }
    this.quantity = quantity;
  }

  /**
   * <p>Retrieves the unit of an ingredient.</p>

   * @return the unit of an ingredient
   */
  public Unit getUnit() {
    return unit;
  }

  /**
   * <p>Sets the unit of an ingredient.</p>

   * @param unit The unit cannot be null empty, and it must be kg, g, l or ml.
   * @throws IllegalArgumentException If the unit null, empty or invalid.
   */
  public void setUnit(Unit unit) {
    if (unit == null) {
      throw new IllegalArgumentException("The unit cannot be null or empty");
    }
    this.unit = unit;
  }

  /**
   * <p>Retrieves the price of an ingredient.</p>

   * @return The price of an ingredient.
   */
  public double getPrice() {
    return price;
  }

  /**
   * <p>Sets the price of the ingredient.</p>

   * @param price The price of the ingredient must be greater than zero.
   * @throws IllegalArgumentException If the price of the ingredient is less than zero.
   */
  public void setPrice(double price) {
    if (price <= 0) {
      throw new IllegalArgumentException("The price of the ingredient cannot be 0 or less");
    }
    this.price = price;
  }

  /**
   * <p>Retrieves the expiration date of an ingredient.</p>

   * @return The expiration date of an ingredient.
   */
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * <p>Sets the expiration date of an ingredient.</p>

   * @param expirationDate The expiration date cannot be null.
   * @throws IllegalArgumentException If the expiration date is null.
   */
  public void setExpirationDate(LocalDate expirationDate) {
    if (expirationDate == null) {
      throw new IllegalArgumentException("The expiration date cannot be null");
    }
    this.expirationDate = expirationDate;
  }
}