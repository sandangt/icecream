for (let i = 0; i < 70; i++) {
	if (i <16) {
		console.log(`insert into products (description,image,name,price,status,category_id) values ("Electronic book", "https://picsum.photos/id/${i}/200/200", "product${i}", "4.13", "1", "1");`);
	}
	else if (i >= 16 && i < 32) {
		console.log(`insert into products (description,image,name,price,status,category_id) values ("Math book", "https://picsum.photos/id/${i}/200/200", "product${i}", "5.9", "1", "2");`);
	}
	else if (i >= 32 && i < 48) {
		console.log(`insert into products (description,image,name,price,status,category_id) values ("Another computer science book", "https://picsum.photos/id/${i}/200/200", "product${i}", "5.21", "1", "3");`);	
	}
	else {
		console.log(`insert into products (description,image,name,price,status,category_id) values ("Economic book", "https://picsum.photos/id/${i}/200/200", "product${i}", "1.15", "1", "4");`);	
	}
}