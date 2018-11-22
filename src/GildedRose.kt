package com.gildedrose

class GildedRose(var items: Array<Item>) {
  private val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  private val sulfuras = "Sulfuras, Hand of Ragnaros"
  private val brie = "Aged Brie"
  private val maxQuality = 50
  private val minQuality = 0
  private val doubleQualityThreshold = 10
  private val tripleQualityThreshold = 5

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
    } else {
      increaseItemQuality(item, when {
        item.sellIn < tripleQualityThreshold -> 3
        item.sellIn < doubleQualityThreshold -> 2
        else -> 1
      })
    }
  }

  private fun increaseItemQuality(item: Item, amount: Int) {
    item.quality = Math.min(maxQuality, item.quality + (1 * amount))
  }

  private fun decreaseItemQuality(item: Item, amount: Int) {
    item.quality = Math.max(minQuality, item.quality - (1 * amount))
  }

  private fun decreaseSellIn(item: Item) {
    item.sellIn--
  }

}

