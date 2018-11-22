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

      if (item.name == brie || item.name == backstagePass) {
        increaseItemQuality(item)
      } else {
        decreaseItemQuality(item)
      }

      decreaseSellIn(item)


      if (isExpired(item)) {
        if (item.name == brie) {
          increaseItemQuality(item)
        } else {
          decreaseItemQuality(item)
        }
      }
    }
  }

  private fun isExpired(item: Item) = item.sellIn < 0

  private fun increaseBackstageQuality(item: Item) {
    if (item.sellIn <= 10) {
      if (item.quality < maxQuality) {
        item.quality += 1
      }
    }

    if (item.sellIn <= 5) {
      if (item.quality < maxQuality) {
        item.quality += 1
      }
    }
  }

  private fun increaseItemQuality(item: Item) {
    if (item.quality < maxQuality) {
      item.quality += 1

      if (item.name == backstagePass) {
        increaseBackstageQuality(item)
      }
    }
  }

  private fun decreaseItemQuality(item: Item) {
    if (item.name == backstagePass) {
      item.quality = 0
      return
    }
    if (item.quality > 0) {
      item.quality -= 1
    }
  }

  private fun decreaseSellIn(item: Item) {
    item.sellIn -= 1
  }

}

