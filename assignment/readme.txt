Tạo database assignment trên cơ sở dữ liệu postgress
Chạy ứng dụng sẽ tự sinh bảng tương ứng với entity

-Một số đề xuất tối ưu performance
-Trong bảng product đang lưu thông tin chung của sản phẩm và số lượng sản phẩm,
trên thực tế thông tin của sp nhiều hơn rất nhiều.Khi số lượng người dùng lớn chẳng hạn 1 triệu user xem sản phẩm
cùng lúc nhưng chỉ có 1 nghìn người mua thì khi có 1 người mua, sẽ update số lượng sp trong db,db sẽ lock row sp này
dẫn đến 1 tr người kia muốn xem sẽ phải chờ.Ý tưởng là tách bảng sp thành 2 bảng,1 bảng chỉ để user xem thông tin,
1 bảng lưu thông tin số lượng sp và giá,khi đó user xem sẽ ko bị ảnh hưởng bới user mua
-Query tìm kiếm theo keywork dạng like %abc% sẽ ko hiệu quả khi số lượng sản phẩm ,đơn hàng lớn,nên đẩy dữ liệu sang
search engine chuyên dụng để search fulltext search như elasticsearch.Việc đồng bộ từ postgress sang elasticsearch có thể
dùng kafka connect.
- một số cách tối ưu khác như cache,sharding db....