# <p align="center"> Quản lí quán cà phê </p>

## 1. Quick link:

* Giao diện người dùng
  * http://localhost:8080 <br>
  *Chỉ xem danh thông tin về quán cà phê.* <br>
  * http://localhost:8080 <br>
  *Xem thông tin về sản phẩm* <br>
 

* Loại bàn
  * http://localhost:8080/admin/typeTable/ <br>
  Xem danh sách loại bàn <br>
  * http://localhost:8080/admin/typeTable/page/1<br>
  admin/typeTable/page/{**pageNumber**} <br>
  *Xem danh sách bàn ở trang* **pageNumber**<br>
  * http://localhost:8080/admin/typeTable/create/ <br>
  *Thêm mới một loại bàn* <br>
  * http://localhost:8080/admin/typeTable/edit/TypeTable02009 <br>
  admin/typeTable/edit/{**idTypeTable**} <br>
  *Sửa thông tin loại bàn có id là* **idTypeTable** <br>
  * http://localhost:8080/admin/typeTable/delete/TypeTable02009 <br>
   admin/typeTable/delete/{**idTypeTable**} <br>
   *Xoá loại bàn dựa vào* **idTypeTable**  <br>
  
* Bàn
  * http://localhost:8080/admin/table/ <br>
  Xem danh sách bàn
  * http://localhost:8080/admin/table/page/1 <br>
  admin/table/page/{**pageNumber**} <br>
  Xem danh sách bàn ở trang **pageNumber** <br>
  * http://localhost:8080/admin/table/create/
  Thêm mới một bàn
  * http://localhost:8080/admin/table/edit/Tablecf04019 <br>
  admin/table/edit/{**idTable**} <br>
  Chỉnh sửa bàn có id là **idTable** <br>
  * http://localhost:8080/admin/table/delete/Tablecf04019 <br>
 admin/table/delete/{**idTable**} <br>
  *Xoá bàn dựa vào* **idTable**
 
 * Nhóm bàn
   * http://localhost:8080/admin/groupTable/ <br>
   *Xem danh sách nhóm bàn*
   * http://localhost:8080/admin/detailGroupTable/view/GTable00001 <br>
   *Xem danh sách các bàn có trong nhóm bàn* **idGTable** <br>
   
 * Sản phẩm
   * http://localhost:8080/admin/detailProduct/view/Product00002* <br>
   *Xem chi tiết những nguyên liệu có trong mộy sản phẩm dựa vào* **IdProduct** <br>

 * Nguyên liệu
   * http://localhost:8080/admin/detailIngredient/view/Ingredient00004 <br>
   *Xem lịch sử thay đổi (tăng giảm) của một nguyên liệu trong kho, dựa vào* **idIngredient** <br>
