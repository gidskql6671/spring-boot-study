package jpabook.jpashop.service

import jpabook.jpashop.domain.item.Book
import jpabook.jpashop.domain.item.Item
import jpabook.jpashop.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class ItemService(
    private val itemRepository: ItemRepository
) {

    @Transactional
    fun saveItem(item: Item) {
        itemRepository.save(item)
    }

    @Transactional
    fun updateItem(itemId: Long, name: String?, price: Int?, stockQuantity: Int) {
        val findItem = itemRepository.findOne(itemId)!! as Book

        findItem.name = name
        findItem.price = price
        findItem.stockQuantity = stockQuantity
    }

    fun findItems(): List<Item> = itemRepository.findAll()

    fun findOne(itemId: Long): Optional<Item> {
        val item = itemRepository.findOne(itemId)

        return Optional.ofNullable(item)
    }
}