package com.gildedrose

class GildedRose(var items: Array<Item>) {

  private val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  private val sulfuras = "Sulfuras, Hand of Ragnaros"
  private val agedBrie = "Aged Brie"
  private val maxQuality = 50

  fun updateQuality() {
    for (item in items) {
      if (item.name != agedBrie && item.name != backstagePass) {
        decreaseItemQuality(item)
      } else if (item.quality < maxQuality) {
        item.quality = item.quality + 1

        if (item.name == backstagePass) {
          increaseBackstageQuality(item)
        }
      }

      decreaseSellIn(item)

      if (item.sellIn < 0) {
        if (item.name != agedBrie) {
          if (item.name != backstagePass) {
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
      increaseItemQuality(item)
    }

    if (item.sellIn < 6) {
      increaseItemQuality(item)
    }
  }

  private fun increaseItemQuality(item: Item) {
    if (item.quality < maxQuality) {
      item.quality = item.quality + 1
    }
  }

  private fun decreaseItemQuality(item: Item) {
    if (item.name != sulfuras && item.quality > 0) {
      item.quality = item.quality - 1
    }
  }

  private fun decreaseSellIn(item: Item) {
    if (item.name != sulfuras) {
      item.sellIn = item.sellIn - 1
    }
  }

}

