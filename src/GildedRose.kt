package com.gildedrose

class GildedRose(var items: Array<Item>) {
  private val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  private val sulfuras = "Sulfuras, Hand of Ragnaros"
  private val brie = "Aged Brie"
  private val maxQuality = 50

  fun updateQuality() {
    for (item in items) {
      if (item.name == sulfuras)
        continue

      decreaseSellIn(item)

      if (item.name == backstagePass) {
        updateBackstageQuality(item)
        continue
      }

      val amount = if (isExpired(item)) 2 else 1

      when {
        item.name == brie -> increaseItemQuality(item, amount)
        else -> decreaseItemQuality(item, amount)
      }
    }
  }

  private fun isExpired(item: Item) = item.sellIn < 0

  private fun updateBackstageQuality(item: Item) {
    if (isExpired(item)) {
      item.quality = 0
      return
    }

    val doubleQualityThreshold = 10
    val tripleQualityThreshold = 5

    increaseItemQuality(item, 1)

    if (item.sellIn < doubleQualityThreshold) {
      increaseItemQuality(item, 1)

      if (item.sellIn < tripleQualityThreshold) {
        increaseItemQuality(item, 1)
      }
    }
  }

  private fun increaseItemQuality(item: Item, amount: Int) {
    item.quality = Math.min(50, item.quality + amount)
  }

  private fun decreaseItemQuality(item: Item, amount: Int) {
    item.quality = Math.max(0, item.quality - amount)
  }

  private fun decreaseSellIn(item: Item) {
    item.sellIn--
  }

}

