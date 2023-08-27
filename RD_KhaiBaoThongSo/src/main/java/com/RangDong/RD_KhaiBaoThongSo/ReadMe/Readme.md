######################################         Quy trình hoạt động               ######################################
# Thêm mới thông số thiết bị
                                        Người dùng chọn mã máy
                                                    |
                                                    V
                       Ứng dụng Chạy vào function tìm kiếm để lấy thông tin mã máy
                                ( table thiet_bi) trong session hoặc DB 
                                                    |
                                                    V
                    Người dùng click "+" để thêm thông số mới và chọn tên thông số
                                                    |
                                                    V
                    Ứng dụng Chạy vào function (--tìm kiếm--) để lấy thông tin của thông 
                        số ( table quan_ly_thong_so) trong session hoặc DB
                                                    |
                                                    V
                    Ứng dụng gán thông tin thông số, mã máy vào object requests
                                    rồi gửi cho server để tạo mới 
                                                    |
                                                    V
                Server xử lý request và response mess "thanh cong" nếu tạo thành công
                                    và "error" nếu không tạo thành công
                                                    |
                                                    V
                        Server thêm mới thông tin thiết bị vào db( table thiet_bi)
                            Thêm mới thông tin thông số thiết bị bào (thong_so_may)
# Del thông số thiết bị
                                Người dùng chọn mã máy để xem thông tin
                                                    |
                                                    V
                        Ứng dụng Chạy vào function tìm kiếm để lấy thông tin mã máy
                                ( table thiet_bi) trong session hoặc DB
                                                    |
                                                    V
                                Người dùng click "del" để xoá thông số máy

                                                    |
                                                    V
                        Ứng dụng gán thông tin thông số, mã máy vào url
                            rồi gửi request cho server để xoá thông số
                                                    |
                                                    V
                    Server xử lý request và response mess "thanh cong" nếu tạo thành công
                                    và "error" nếu không tạo thành công
                                                    |
                                                    V
                        Server xoá thông tin thông số trong  db( table thong_so_may)
# Thêm mới, và cập nhật thông số cho kịch bản

                            Người dùng click biểu tượng "+" để thêm mới kịch bản
                                                    |
                                                    V
                            Người dùng nhập mã sản phẩm và vesion sản phẩm
                                    Sau đó xác nhận tạo mới kịch bản
                                                    |
                                                    V
Người dùng chọn
                Người dùng chọn mã thiết bị -> màn hình sẽ hiện thông tin loại thiết bị
                                    và danh sách thông số tương ứng

                                                    |
                                                    V
        Người dùng có thể tuỳ chỉnh (thêm, sửa, xoá) thông số, và điền giá trị cho thông số
                                                    |
                                                    V
    Sau khi nhập giá trị, người dùng click vào button "xác nhận" để gửi yêu cầu thêm mới kịch bản
                                                    |
                                                    V
        Server sẽ cập nhật dữ liệu sau khi nhận được request và gửi response về front-end