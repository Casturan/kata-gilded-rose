package com.gildedrose

class GildedRose(var items: Array<Item>) {

  private val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  private val sulfuras = "Sulfuras, Hand of Ragnaros"
  private val agedBrie = "Aged Brie"

  fun updateQuality() {
    for (item in items) {
      if (item.name != agedBrie && item.name != backstagePass) {

        if (item.quality > 0) {
          if (item.name != sulfuras) {
            item.quality = item.quality - 1
          }
        }

      } else {

        if (item.quality < 50) {
          item.quality = item.quality + 1

          if (item.name == backstagePass) {
            if (item.sellIn < 11) {
              if (item.quality < 50) {
                item.quality = item.quality + 1
              }
            }

            if (item.sellIn < 6) {
              if (item.quality < 50) {
                item.quality = item.quality + 1
              }
            }
          }
        }

      }



      decreaseSellIn(item)



      if (item.sellIn < 0) {
        if (item.name != agedBrie) {
          if (item.name != backstagePass) {
            if (item.quality > 0) {
              if (item.name != sulfuras) {
                item.quality = item.quality - 1
              }
            }
          } else {
            item.quality -= item.quality
          }
        } else {
          if (item.quality < 50) {
            item.quality = item.quality + 1
          }
        }
      }

    }
  }

  private fun decreaseSellIn(item: Item) {
    if (item.name != sulfuras) {
      item.sellIn = item.sellIn - 1
    }
  }

}

