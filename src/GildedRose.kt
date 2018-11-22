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

      var amount = 1
      if (isExpired(item)) {
        amount = 2
      }

      when {
        item.name == brie -> increaseItemQuality(item)
        else -> decreaseItemQuality(item)
      }
      if (isExpired(item)) {
        when {
          item.name == brie -> increaseItemQuality(item)
          else -> decreaseItemQuality(item)
        }
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

    increaseItemQuality(item)

    if (item.sellIn < doubleQualityThreshold) {
      increaseItemQuality(item)

      if (item.sellIn < tripleQualityThreshold) {
        increaseItemQuality(item)
      }
    }
  }

  private fun increaseItemQuality(item: Item) {
    if (item.quality < maxQuality) {
      item.quality++
    }
  }

  private fun decreaseItemQuality(item: Item) {
    if (item.quality > 0) {
      item.quality--
    }
  }

  private fun decreaseSellIn(item: Item) {
    item.sellIn--
  }

}

