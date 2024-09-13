document.addEventListener('DOMContentLoaded', () => {
    const cart = [];
    const cartIcon = document.getElementById('cartIcon');
    const cartPopup = document.getElementById('cartPopup');
    const cartItemsList = document.getElementById('cartItems');
    const cartCount = document.getElementById('cartCount');
    const orderButton = document.getElementById('orderButton');
    const productList = document.getElementById('productList');
    const searchInput = document.getElementById('search');
    const categoryFilter = document.getElementById('categoryFilter');

    const products = [
        { name: 'Яблоки зеленые', price: 400, category: 'Фрукты', image: 'img/apple.jpg' },
        { name: 'Картошка не мытая', price: 125, category: 'Овощи', image: 'img/potato.png' },
        { name: 'Творог 12% жира', price: 750, category: 'Молочные продукты', image: 'img/cottage_cheese.jpg' },
        { name: 'Рис длиннозерный', price: 275, category: 'Зерновые', image: 'img/rice.jpg' },
        { name: 'Говядина', price: 2900, category: 'Мясо', image: 'img/beef.jpg' },
        { name: 'Груши', price: 1560, category: 'Фрукты', image: 'img/pear.jpg' },
        { name: 'Репчатый лук', price: 110, category: 'Овощи', image: 'img/onion.jpg' },
        { name: 'Сыр "Российский"', price: 2100, category: 'Молочные продукты', image: 'img/сhees.jpg' },
        { name: 'Гречка', price: 410, category: 'Зерновые', image: 'img/Buckwheat.jpg' },
        { name: 'Курица', price: 1375, category: 'Мясо', image: 'img/сhicken.jpg' },
        { name: 'Бананы', price: 575, category: 'Фрукты', image: 'img/banan.jpg' },
        { name: 'Капуста белокочанная', price: 90, category: 'Овощи', image: 'img/cabbage.jpg' },
        { name: 'Сметана 20% жира', price: 1000, category: 'Молочные продукты', image: 'img/sour_cream.png' },
        { name: 'Кукуруза зерновая', price: 85, category: 'Зерновые', image: 'img/corn.png' },
        { name: 'Свинина', price: 1750, category: 'Мясо', image: 'img/pig.jpg' },
        { name: 'Апельсины', price: 900, category: 'Фрукты', image: 'img/orange.jpg' },
        { name: 'Помидоры', price: 395, category: 'Овощи', image: 'img/tomato.jpg' },
        { name: 'Сливочное масло 82% жира', price: 1600, category: 'Молочные продукты', image: 'img/butter.jpg' },
        { name: 'Пшеничная крупа', price: 140, category: 'Зерновые', image: 'img/wheat.jpg' },
        { name: 'Баранина', price: 1165, category: 'Мясо', image: 'img/mutton.jpg' },
        // Добавьте дополнительные товары сюда
    ];

    
    function loadProducts() {
        productList.innerHTML = ''; 
        products.forEach(product => {
            const productDiv = document.createElement('div');
            productDiv.classList.add('product');
            productDiv.dataset.category = product.category;
            productDiv.innerHTML = `
                <img src="${product.image}" alt="${product.name}">
                <h2>${product.name}</h2>
                <p>Цена: ${product.price} руб./5кг.</p>
                <button class="add-to-cart">Добавить в корзину</button>
            `;
            productList.appendChild(productDiv);
        });

    
        document.querySelectorAll('.add-to-cart').forEach(button => {
            button.addEventListener('click', (event) => {
                const product = event.target.closest('.product');
                const productName = product.querySelector('h2').textContent;
                
                cart.push(productName);
                updateCart();
            });
        });
    }


    function updateCart() {
        cartItemsList.innerHTML = '';
        cart.forEach(item => {
            const li = document.createElement('li');
            li.textContent = item;
            cartItemsList.appendChild(li);
        });
        cartCount.textContent = cart.length;
    }

    cartIcon.addEventListener('click', () => {
        cartPopup.classList.toggle('hidden');
    });

    orderButton.addEventListener('click', () => {
        alert('Спасибо за заказ!');
        cart.length = 0; 
        updateCart();
        cartPopup.classList.add('hidden');
    });


    searchInput.addEventListener('input', filterProducts);
    categoryFilter.addEventListener('change', filterProducts);

    function filterProducts() {
        const searchText = searchInput.value.toLowerCase();
        const selectedCategory = categoryFilter.value.toLowerCase();

        document.querySelectorAll('.product').forEach(product => {
            const productName = product.querySelector('h2').textContent.toLowerCase();
            const productCategory = product.dataset.category.toLowerCase();
            const matchesSearch = productName.includes(searchText);
            const matchesCategory = selectedCategory === '' || productCategory === selectedCategory;

            if (matchesSearch && matchesCategory) {
                product.style.display = '';
            } else {
                product.style.display = 'none';
            }
        });
    }

    loadProducts();
});
