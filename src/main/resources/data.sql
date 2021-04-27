insert into Category(CateId,CateName) values (1,'food');
insert into Menu(MenuId,Menuname,Descript,Costl,Image_Path,Category_CateId) values (1,'Steak','Nice Meat',200.5,'test',1);
insert into Menu(MenuId,Menuname,Descript,Costl,Image_Path,Category_CateId) values (2,'Curry','Just Curry',300.56,'test2',1);
insert into Sizes(SizeId,SizeName) values (1,'xl');
insert into Sizes(SizeId,SizeName) values (2,'l');
insert into Sizes(SizeId,SizeName) values (3,'sl');
insert into Menu_has_size(size_id,menu_id) values (1,1);
insert into Menu_has_size(size_id,menu_id) values (2,1);
insert into Menu_has_size(size_id,menu_id) values (3,1);

