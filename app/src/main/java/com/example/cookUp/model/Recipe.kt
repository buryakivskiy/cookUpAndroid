package com.example.cookUp.model

import androidx.compose.runtime.Immutable

@Immutable
data class Recipe(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val tagline: String = "",
    val description: String = "",
    val ingredients: String = "",
    val tags: Set<String> = emptySet()
)


val recipes = listOf(
    Recipe(
        id = 1L,
        name = "Спагеті з грибами та тим*яном",
        tagline = "Ароматні спагеті для грибних поціновувачів",
        description = "Спагеті з грибами та тим*яном — це простий, але дуже смачний рецепт, що поєднує ароматність тим*яну та ніжність грибів. Підсмажте їх у вершковому соусі та подайте з вареними спагеті...",
        ingredients = "Спагеті, гриби, тим*ян, вершки, часник, пармезан, оливкова олія, сіль, перець",
        imageUrl = "https://milaclub.com/uploads/2019/07/milaclub-spagetti-s-gribami-bolgarskim-pertsem-i-tsukini.jpg"
    ),

    Recipe(
        id = 2L,
        name = "Шоколадний фондан з ванільним морозивом",
        tagline = "Ніжний шоколадний десерт з внутрішнім сюрпризом",
        description = "Шоколадний фондан — це десерт для справжніх гурманів. З внутрішнім таємством рідкої шоколадної начинки, цей десерт точно розтопить ваші смакові рецептори. Подається з ванільним морозивом...",
        ingredients = "Шоколад, вершки, цукор, яйця, мука, ванільне морозиво",
        imageUrl = "https://images.unian.net/photos/2020_12/thumb_files/400_0_1608796072-3763.jpg?0.7495948594539974"
    ),

    Recipe(
        id = 3L,
        name = "Кролик на грилі з медово-гірчичним глазурю",
        tagline = "Легка та смачна альтернатива м*ясу",
        description = "Кролик на грилі з медово-гірчичним глазурю — це елегантне та низькокалорійне блюдо. З гірчично-медовим глазурю, воно стає справжньою родзинкою вашого столу...",
        ingredients = "Кролик, мед, гірчиця, соєвий соус, лимон, часник, оливкова олія, сіль, перець",
        imageUrl = "https://whogrill.ru/upload/iblock/8a0/8a04c4ca401904ccf1df85274e2790f1.png"
    ),

    Recipe(
        id = 4L,
        name = "Фруктовий салат з м*ятно-лимонним соусом",
        tagline = "Літній настрій у кожній ложці",
        description = "Фруктовий салат з м*ятно-лимонним соусом — це освіжаючий і ароматний салат для літніх днів. З додаванням м*яти та лимонного соусу, цей салат стане вашим улюбленим варіантом легкого перекусу...",
        ingredients = "Ягоди, ананас, виноград, м*ята, лимон, мед, гранат, киві",
        imageUrl = "https://drugpovar.ru/wp-content/uploads/2019/12/Zimnij-fruktovyj-salat-s-limonno-makovoj-zapravkoj.jpg"
    ),

    Recipe(
        id = 5L,
        name = "Маргарита з класичним таємством",
        tagline = "Просто, але неперевершено",
        description = "Маргарита з класичним таємством — це ідеальний коктейль для вечірнього відпочинку. З класичним смаком та додаванням освіжаючого лаймового льоду, цей напій точно вас вразить...",
        ingredients = "Текіла, трипл сек, лайм, апельсиновий лікер, лід",
        imageUrl = "https://cdn.segodnya.ua/i/original/media/image/628/767/7b7/6287677b7481a.jpg.webp"
    ),

    Recipe(
        id = 6L,
        name = "Карі з куркою та кокосовим молоком",
        tagline = "Екзотика смаку в кожній ложці",
        description = "Карі з куркою та кокосовим молоком — це парадіз для поціновувачів азійської кухні. З ароматним карі та ніжним кокосовим молоком, ця страва стане вашим улюбленим гостинцем...",
        ingredients = "Куряче філе, кокосове молоко, карі, цибуля, морква, картошка, імбир, часник, соєвий соус",
        imageUrl = "https://novy.tv/wp-content/uploads/sites/96/2020/01/iStock-177126541.jpg"
    ),

    Recipe(
        id = 7L,
        name = "Грушевий тарт з горіховим кремом",
        tagline = "Десерт, який танцює на вашому пальці",
        description = "Грушевий тарт з горіховим кремом — це витончений десерт для цінителів вишуканості. З соковитими грушами та ніжним горіховим кремом, цей тарт стане прикрасою будь-якого святкового столу...",
        ingredients = "Тісто для тарту, груші, мед, горіховий крем, вершки, цукор",
        imageUrl = "https://madamevorger.com/wp-content/uploads/2020/11/peartart2-e1663770407981.jpg"
    ),

    Recipe(
        id = 8L,
        name = "Крем-суп з броколі та сиром чеддер",
        tagline = "Тепло і смачно в одній ложці",
        description = "Крем-суп з броколі та сиром чеддер — це смачний і ситний суп для холодних днів. З ароматним броколі та плавним сиром чеддер, він зігріє вас своєю ніжністю...",
        ingredients = "Броколі, картопля, цибуля, чеддер, вершки, часник, оливкова олія, сіль, перець",
        imageUrl = "https://static.1000.menu/img/content/20310/-chedder-sup-chedder_1494268959_1_max.jpg"
    ),

    Recipe(
        id = 9L,
        name = "Різотто з грибами та пармезаном",
        tagline = "Італійська класика на вашому тарілці",
        description = "Різотто з грибами та пармезаном — це класична італійська страва, яка підкорить вас ніжністю та ароматом. З арборіо рисом, грибами та пармезаном, це різотто точно стане улюбленим блюдом...",
        ingredients = "Рис арборіо, гриби, бульйон, біле вино, пармезан, цибуля, вершки, часник, оливкова олія, сіль, перець",
        imageUrl = "https://rud.ua/uploads/under_recipe/600x300.jpg"
    ),
    Recipe(
        id = 10L,
        name = "Фаршировані печериці з сиром та шпинатом",
        tagline = "Експлозія смаку в кожному кукурудзі",
        description = "Фаршировані печериці з сиром та шпинатом — це закуска, яка швидко зникне з вашого столу. З ніжним сиром, ароматним шпинатом та грибами, це блюдо стане ідеальним вибором для будь-якого заходу...",
        ingredients = "Печериці, сир фета, шпинат, часник, оливкова олія, сіль, перець",
        imageUrl = "https://ukr.media/static/ba/aimg/4/0/5/405944_1.jpg"
    ),
    Recipe(
        id = 11L,
        name = "Борщ",
        tagline = "Неперевершено смачний борщ",
        description = "Борщ — це кулінарний шедевр, що втілює в собі українську традицію та багатство смаку. Приготування борщу — це справжнє мистецтво, але ми поділимося з вами детальним рецептом крок за кроком...",
        ingredients = "Свиняча шия або кістка, буряки, картопля, цибуля, морква, капуста, томатна паста, часник, сіль, перець, лавровий лист",
        imageUrl = "https://images.unian.net/photos/2022_09/thumb_files/1200_0_1662892107-3846.jpg"
    ),

    Recipe(
        id = 12L,
        name = "Паста з соусом Болоньєзе",
        tagline = "Смачна класика з Італії",
        description = "Паста з соусом Болоньєзе - класична італійська страва, яка завоювала популярність в усьому світі. Цей рецепт дозволяє вам насолоджуватися найсмачнішою пастою прямо вдома...",
        ingredients = "Макарони, мясний фарш, цибуля, часник, томатна паста, вино червоне сухе, оливкова олія, сіль, перець, базилік",
        imageUrl = "https://i.obozrevatel.com/food/recipemain/2018/12/29/item4162.jpg?size=636x424"
    ),

    Recipe(
        id = 13L,
        name = "Салат \"Цезар\"",
        tagline = "Класичний смак у кожному кусочку",
        description = "Цезар — це класичний салат, який завжди вражає своєю смаковою гармонією. З курячим м*ясом, хрусткими сухариками і неперевершеним соусом, цей салат стане відмінним варіантом для будь-якого обіду або вечері...",
        ingredients = "Куряче філе, листя салату, помідори, сир пармезан, хліб, оливкова олія, гірчиця, яйця, часник, сіль, перець",
        imageUrl = "https://images.unian.net/photos/2019_02/thumb_files/1000_545_1551364909-4775.jpg"
    ),

    Recipe(
        id = 14L,
        name = "Оселедець під шубою",
        tagline = "Святковий смак на вашому столі",
        description = "Оселедець під шубою — це традиційна російська страва, яка завжди підкреслює святковий настрій. Шари картоплі, маринованої риби, овочів і майонезу створюють справжню гастрономічну втіху...",
        ingredients = "Оселедець, картопля, морква, цибуля, яйця, буряк, майонез, сіль, перець",
        imageUrl = "https://img.tsn.ua/cached/271/tsn-2d42605a5d3a686abfa749ad9c360aad/thumbs/1200x630/05/5d/197334d3557063bcdf5b048c20fb5d05.jpeg"
    ),

    Recipe(
        id = 15L,
        name = "Ласощі з лосося",
        tagline = "Ніжний смак морської деликатеси",
        description = "Ця страва ідеально поєднує ніжне лосось і свіжі овочі. Легко приготовлюється та стане прекрасним варіантом для ваших вечірок або родинних обідів...",
        ingredients = "Лосось, лимон, оливкова олія, зелень (кинза або петрушка), спеції, сіль, перець",
        imageUrl = "https://rud.ua/uploads/under_recipe/rybnoe-file-v-dukhovke-s-ovoshchami.jpg"
    ),

    Recipe(
        id = 16L,
        name = "Суп \"Мінестроне\"",
        tagline = "Теплий і ситий італійський смак",
        description = "Мінестроне — це традиційний італійський суп, багатий овочами та ароматними травами. Його смакові якості роблять його відмінним вибором для зимових вечорів...",
        ingredients = "Брокколі, морква, цибуля, картопля, помідори, кавові ложки олії, вершки, макарони, сіль, перець",
        imageUrl = "https://i.obozrevatel.com/food/recipemain/2019/3/22/vb.jpg"
    ),

    Recipe(
        id = 17L,
        name = "Грецький салат",
        tagline = "Легкий смак сонячної Греції",
        description = "Грецький салат — це симфонія свіжих овочів, олив та сирного смаку. Його легкість і освіжаючість роблять його відмінним вибором влітку...",
        ingredients = "Помідори, огірки, фета, чорні оливи, червона цибуля, оливкова олія, оцет, сіль, орегано",
        imageUrl = "https://horodok.city/upload/article/kAhOV8LQStsPeOhgTUxr.jpg"
    ),

    Recipe(
        id = 18L,
        name = "Тайський суп Том Ям",
        tagline = "Екзотика в кожному ковтку",
        description = "Том Ям — це традиційний тайський суп, який вражає своєю ароматною і гострою смаковою палітрою. З сочними креветками та свіжими травами, цей суп припаде до смаку любителям екзотичних страв...",
        ingredients = "Креветки, лимонна трава, гострий перець, гриби шиитаке, корінь галангалу, листя каффір-лайм, рибний соус, лайм, сіль",
        imageUrl = "https://images.prom.ua/1205383928_w600_h600_tom-yam-.jpg"
    ),

    Recipe(
        id = 19L,
        name = "Шоколадний торт \"Молочний ластівки\"",
        tagline = "Солодка насолода для справжніх гурманів",
        description = "Цей торт - справжня розкіш для любителів шоколаду. З ніжним шаром молочного крему та шоколадним ганашем, він прекрасно підійде для святкового столу або просто для задоволення своїх шоколадних захоплень...",
        ingredients = "Шоколад, масло вершкове, цукор, яйця, мука, какао, молоко, вершки, ваніль, сіль",
        imageUrl = "https://i.ytimg.com/vi/DMHJuwCuqtw/maxresdefault.jpg"
    ),

    Recipe(
        id = 20L,
        name = "Лимонад з базиліком та малиною",
        tagline = "Свіжість та смак у кожному ковтку",
        description = "Лимонад - це чудовий напій для освіження у спекотний день. Додайте до нього ароматний базилік та солодку малину, і ви отримаєте ідеальний варіант для літнього настрою...",
        ingredients = "Лимони, вода, цукор, базилік, малина, лід",
        imageUrl = "https://cdn.segodnya.ua/i/original/media/image/5ef/f2c/6cc/5eff2c6cc434d.jpg"
    ),
    Recipe(
        id = 21L,
        name = "Apples",
        tagline = "Смачна яблучна насолода",
        imageUrl = "https://source.unsplash.com/1d9xXWMtQzQ",
        description = "КРОК 1: Насолоджуйтеся свіжістю та солодкістю свіжих яблук з цим простим рецептом.\n" +
                "\n" +
                "КРОК 2: Їжте їх самостійно або використовуйте в різноманітних стравах.\n" +
                "\n" +
                "КРОК 3: Додайте трошки кориці та цукру, щоб підкреслити смак.\n" +
                "\n" +
                "КРОК 4: Смачного!",
        ingredients = "Яблука, кориця, цукор"
    ),
    Recipe(
        id = 22L,
        name = "Яблучний соус",
        tagline = "Ароматне яблучне пюре",
        imageUrl = "https://source.unsplash.com/wZxpOw84QTU",
        description = "КРОК 1: Приготуйте власне яблучне пюре вдома за допомогою цього простого рецепту.\n" +
                "\n" +
                "КРОК 2: Ідеально як закуска, гарнір або інгредієнт для випічки.\n" +
                "\n" +
                "КРОК 3: Додайте цукор за смаком, якщо потрібно.\n" +
                "\n" +
                "КРОК 4: Спробуйте його на хлібі чи використовуйте в різних рецептах.",
        ingredients = "Яблука, вода, цукор (за бажанням)"
    ),
    Recipe(
        id = 23L,
        name = "Яблучні чіпси",
        tagline = "Хрусткі яблучні чіпси",
        imageUrl = "https://source.unsplash.com/okzeRxm_GPo",
        description = "КРОК 1: Печіть хрусткі та смачні яблучні чіпси за допомогою цього здоров'ями рецепту.\n" +
                "\n" +
                "КРОК 2: Чудова альтернатива звичайним чіпсам.\n" +
                "\n" +
                "КРОК 3: Додайте корицю та цукор, якщо хочете солодкість.\n" +
                "\n" +
                "КРОК 4: Смачного і здорового перекусу!",
        ingredients = "Яблука, кориця, цукор (за бажанням)"
    ),
    Recipe(
        id = 24L,
        name = "Яблучний сік",
        tagline = "Свіже яблучне сок",
        imageUrl = "https://source.unsplash.com/l7imGdupuhU",
        description = "КРОК 1: Загасіть свою спрагу свіжим яблучним соком.\n" +
                "\n" +
                "КРОК 2: Приготуйте його вдома, натурально і освіжаюче.\n" +
                "\n" +
                "КРОК 3: Додайте цукор за смаком, якщо хочете солодкість.\n" +
                "\n" +
                "КРОК 4: Подавайте охолодженим і насолоджуйтеся натуральним смаком.",
        ingredients = "Яблука, вода, цукор (за бажанням)"
    ),
    Recipe(
        id = 25L,
        name = "Яблучний пиріг",
        tagline = "Класичний яблучний пиріг",
        imageUrl = "https://source.unsplash.com/bkXzABDt08Q",
        description = "КРОК 1: Приготуйте класичний яблучний пиріг за цим безсмертним рецептом.\n" +
                "\n" +
                "КРОК 2: Сполучення солодких і кислих яблук, теплих спецій та хрусткого тіста робить його неймовірно смачним.\n" +
                "\n" +
                "КРОК 3: Подаємо з морозивом або вершками за бажанням.\n" +
                "\n" +
                "КРОК 4: Смачного!",
        ingredients = "Тісто для пирога, яблука, цукор, кориця"
    ),
)
