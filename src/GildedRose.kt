package com.gildedrose

class GildedRose(var items: Array<Item>) {
  private val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  private val sulfuras = "Sulfuras, Hand of Ragnaros"
  private val brie = "Aged Brie"
  private val maxQuality = 50
  private val minQuality = 0
  private val doubleQualityThreshold = 10
  private val tripleQualityThreshold = 5
  private val tripleRate = 3
  private val doubleRate = 2
  private val normalRate = 1

  fun updateQuality() {
    items.filter { it.name != sulfuras }
        .forEach { item ->
          decreaseSellIn(item)
          when (item.name) {
            brie -> increaseItemQuality(item, qualityChangeRate(item))
            backstagePass -> updateBackstageQuality(item)
            else -> decreaseItemQuality(item, qualityChangeRate(item))
          }
        }
  }

  private fun qualityChangeRate(item: Item) = if (isExpired(item)) doubleRate else normalRate

  private fun isExpired(item: Item) = item.sellIn < 0

  private fun updateBackstageQuality(item: Item) {
    if (isExpired(item)) {
      item.quality = minQuality
    } else {
      increaseItemQuality(item, when {
        item.sellIn < tripleQualityThreshold -> tripleRate
        item.sellIn < doubleQualityThreshold -> doubleRate
        else -> normalRate
      })
    }
  }

  private fun increaseItemQuality(item: Item, increaseRate: Int) {
    item.quality = Math.min(maxQuality, item.quality + (1 * increaseRate))
  }

  private fun decreaseItemQuality(item: Item, decreaseRate: Int) {
    item.quality = Math.max(minQuality, item.quality - (1 * decreaseRate))
  }

  private fun decreaseSellIn(item: Item) {
    item.sellIn--
  }

}

