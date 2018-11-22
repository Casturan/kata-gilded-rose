package com.gildedrose

class GildedRose(var items: Array<Item>) {
  private val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  private val sulfuras = "Sulfuras, Hand of Ragnaros"
  private val brie = "Aged Brie"
  private val maxQuality = 50
  private val minQuality = 0

  private fun sulfurasLogic(item: Item) {

  }
  private fun brieLogic(item: Item) {
    decreaseSellIn(item)
    val amount = if (isExpired(item)) 2 else 1
    increaseItemQuality(item, amount)
  }
  private fun backstagePassLogic(item: Item) {
    decreaseSellIn(item)
    updateBackstageQuality(item)
  }
  private fun defaultItemLogic(item: Item) {
    decreaseSellIn(item)
    val amount = if (isExpired(item)) 2 else 1
    decreaseItemQuality(item, amount)
  }

  fun updateQuality() {
    for (item in items) {
      when(item.name) {
        brie -> brieLogic(item)
        backstagePass -> backstagePassLogic(item)
        !in sulfuras -> defaultItemLogic(item)
      }
    }
  }

  private fun isExpired(item: Item) = item.sellIn < 0

  private fun updateBackstageQuality(item: Item) {
    if (isExpired(item)) {
      item.quality = minQuality
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
    item.quality = Math.min(maxQuality, item.quality + amount)
  }

  private fun decreaseItemQuality(item: Item, amount: Int) {
    item.quality = Math.max(minQuality, item.quality - amount)
  }

  private fun decreaseSellIn(item: Item) {
    item.sellIn--
  }

}

