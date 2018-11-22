package com.gildedrose

class GildedRose(var items: Array<Item>) {
  private val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  private val sulfuras = "Sulfuras, Hand of Ragnaros"
  private val brie = "Aged Brie"
  private val maxQuality = 50
  private val minQuality = 0

  fun updateQuality() {
    items.filter { it.name != sulfuras }
        .forEach { item ->
          decreaseSellIn(item)
          when (item.name) {
            brie -> increaseItemQuality(item, calculateChangeAmount(item))
            backstagePass -> updateBackstageQuality(item)
            else -> decreaseItemQuality(item, calculateChangeAmount(item))
          }
        }
  }

  private fun calculateChangeAmount(item: Item) = if (isExpired(item)) 2 else 1

  private fun isExpired(item: Item) = item.sellIn < 0

  private fun updateBackstageQuality(item: Item) {
    if (isExpired(item)) {
      item.quality = minQuality
      return
    }

    val doubleQualityThreshold = 10
    val tripleQualityThreshold = 5

    val amount: Int

    if (item.sellIn < tripleQualityThreshold) {
      amount = 3
    } else if (item.sellIn < doubleQualityThreshold) {
      amount = 2
    } else {
      amount = 1
    }
    increaseItemQuality(item, amount)
  }

  private fun increaseItemQuality(item: Item, amount: Int) {
    item.quality = Math.min(maxQuality, item.quality + amount)
  }

  private fun decreaseItemQuality(item: Item, amount: Int) {
    item.quality = Math.max(minQuality, item.quality - amount)
  }

  private fun decreaseSellIn(item: Item) {
    item.sellIn--
  }

}

