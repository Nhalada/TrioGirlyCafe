package com.project.triogirlycafe.models

data class DrinksModel(
    var id: Int = 0,
    var title: String = "no title",
    var img: String = "no img",
    var price: Double = 0.0,
    var rate: Double = 0.0,
    var discount: Double = 0.0,
    var category: String = "no category", // Added category field
    var description: String = "no description", // Added description field

)



// Updated drinksList with café items and their descriptions
val drinksList: List<DrinksModel> = listOf(
    DrinksModel(
        id = 1,
        title = "Hot Cappuccino",
        img = "https://hamiltonbeach.com/media/recipes/cappucino-fotoliastock.jpg",
        price = 3.0,
        rate = 4.5,
        discount = 20.0,
        category = "Hot Coffee",
        description = "A rich and creamy cappuccino made with freshly brewed espresso, steamed milk, and topped with a layer of frothy foam.",

    ),
    DrinksModel(
        id = 2,
        title = "Macchiato",
        img = "https://i.pinimg.com/736x/e4/ce/de/e4cede085337c89cacebbdefd770cec0.jpg",
        price = 3.8,
        rate = 4.7,
        discount = 15.0,
        category = "Cold Coffee",
        description = "A refreshing iced drink made with espresso, milk, and a drizzle of caramel syrup, perfect for hot days.",

    ),
    DrinksModel(
        id = 3,
        title = "Chocolate Frappe",
        img = "https://i.pinimg.com/736x/e7/42/29/e742295cb829217421ba41b9c73eb62b.jpg",
        price = 4.2,
        rate = 4.6,
        discount = 10.0,
        category = "Healthy Drinks",
        description = "A cool, blended drink made with rich chocolate, coffee, and ice, topped with whipped cream for an extra indulgence.",

    ),
    DrinksModel(
        id = 4,
        title = "Greentea Latte",
        img = "https://i.pinimg.com/736x/b4/7d/65/b47d6558720dbc7daa90d0f48ea73826.jpg",
        price = 3.5,
        rate = 4.3,
        discount = 25.0,
        category = "Healthy Drinks",
        description = "A smooth latte made with matcha green tea powder, steamed milk, and a touch of sweetness for a calming, healthy drink.",

    ),
    DrinksModel(
        id = 5,
        title = "Blueberry Frappe",
        img = "https://i.pinimg.com/736x/40/10/23/401023f967f44c1353fac8effc964f17.jpg",
        price = 5.0,
        rate = 4.9,
        discount = 30.0,
        category = "Healthy Drinks",
        description = "A refreshing and creamy blended drink made with blueberries, ice, and a touch of sweetness, topped with whipped cream for a perfect dessert indulgence.",

    ),
    DrinksModel(
        id = 6,
        title = "Latte",
        img = "https://i.pinimg.com/736x/4e/e4/f1/4ee4f18c45f2f431723448c4693dbe82.jpg",
        price = 3.5,
        rate = 4.4,
        discount = 10.0,
        category = "Cold Coffee",
        description = "A smooth blend of freshly brewed espresso and cold milk served over ice, perfect for a refreshing pick-me-up.",
    ),
    DrinksModel(
        id = 7,
        title = "Vanilla Bean",
        img = "https://i.pinimg.com/736x/3d/b8/a7/3db8a7c02e0b92ba9b9914b83135bf7c.jpg",
        price = 4.0,
        rate = 4.8,
        discount = 20.0,
        category = "Healthy Drinks",
        description = "A sweet, vanilla-flavored frappe blended with ice and topped with whipped cream for an extra indulgent treat.",
    ),
    DrinksModel(
        id = 8,
        title = "Strawberry Smoothie",
        img = "https://i.pinimg.com/736x/88/96/7c/88967cdee36ee1a969b95c3f024712d1.jpg",
        price = 3.7,
        rate = 4.6,
        discount = 15.0,
        category = "Healthy Drinks",
        description = "A refreshing blend of fresh strawberries and yogurt, perfect for a healthy, fruity snack.",
    ),
    DrinksModel(
        id = 9,
        title = "Matcha Latte",
        img = "https://i.pinimg.com/736x/4b/7c/ef/4b7cefeb3f8f3e89f95a980f60a950cd.jpg",
        price = 4.5,
        rate = 4.3,
        discount = 10.0,
        category = "Healthy Drinks",
        description = "A vibrant, cold drink made with matcha green tea powder and milk, offering a calming yet energizing experience.",
    ),
    DrinksModel(
        id = 10,
        title = "Caramel Hazelnut",
        img = "https://i.pinimg.com/736x/f9/55/3d/f9553d624750d7f12705460f4078dc1d.jpg",
        price = 4.3,
        rate = 4.6,
        discount = 20.0,
        category = "Cold Coffee",
        description = "A delicious espresso-based drink with caramel syrup and steamed milk, topped with a rich layer of frothy foam.",
    )
)

