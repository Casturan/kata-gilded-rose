package com.gildedrose

class GildedRose(var items: Array<Item>) {

  private val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
  private val SULFURAS = "Sulfuras, Hand of Ragnaros"
  private val BRIE = "Aged Brie"
  private val maxQuality = 50

  fun updateQuality() {
    for (item in items) {
      if (item.name == SULFURAS)
        continue

      if (item.name != BRIE && item.name != BACKSTAGE_PASS) {
        decreaseItemQuality(item)
      } else {
        increaseItemQuality(item)
      }

      decreaseSellIn(item)

      if (item.sellIn < 0) {
        if (item.name != BRIE) {
          if (item.name != BACKSTAGE_PASS) {
            decreaseItemQuality(item)
          } else {
            item.quality = 0
          }
        } else {
          increaseItemQuality(item)
        }
      }
    }
  }

  private fun increaseBackstageQuality(item: Item) {
    if (item.sellIn < 11) {
      if (item.quality < maxQuality) {
        item.quality += 1
      }
    }

    if (item.sellIn < 6) {
      if (item.quality < maxQuality) {
        item.quality += 1
      }
    }
  }

  private fun increaseItemQuality(item: Item) {
    if (item.quality < maxQuality) {
      item.quality += 1

      if (item.name == BACKSTAGE_PASS) {
        increaseBackstageQuality(item)
      }
    }
  }

  private fun decreaseItemQuality(item: Item) {
    if (item.quality > 0) {
      item.quality -= 1
    }
  }

  private fun decreaseSellIn(item: Item) {
    item.sellIn -= 1
  }

}

