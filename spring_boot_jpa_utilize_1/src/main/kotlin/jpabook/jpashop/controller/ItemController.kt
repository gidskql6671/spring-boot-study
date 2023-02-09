package jpabook.jpashop.controller

import jpabook.jpashop.domain.item.Book
import jpabook.jpashop.dto.BookForm
import jpabook.jpashop.service.ItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/items")
class ItemController(
    private val itemService: ItemService
) {

    @GetMapping("/new")
    fun createForm(model: Model): String {
        model.addAttribute("form", BookForm())
        return "items/createItemForm"
    }

    @PostMapping("/new")
    fun create(form: BookForm): String {
        val book = Book(
            name = form.name,
            price = form.price,
            stockQuantity = form.stockQuantity ?: 0,
            author = form.author,
            isbn = form.isbn
        )

        itemService.saveItem(book)
        return "redirect:/items"
    }

    @GetMapping("")
    fun list(model: Model): String {
        val items = itemService.findItems()
        model.addAttribute("items", items)
        return "items/itemList"
    }

    @GetMapping("/{itemId}/edit")
    fun updateItemForm(@PathVariable("itemId") itemId: Long, model: Model): String {
        val item = itemService.findOne(itemId).get() as Book

        val form =
            with(item) {
                BookForm(id, name, price, stockQuantity, author, isbn)
            }

        model.addAttribute("form", form)
        return "items/updateItemForm"
    }

    @PostMapping(value = ["/{itemId}/edit"])
    fun updateItem(
        @PathVariable("itemId") itemId: Long,
        @ModelAttribute("form") form: BookForm
    ): String {
        itemService.updateItem(itemId, form)

        return "redirect:/items"
    }
}