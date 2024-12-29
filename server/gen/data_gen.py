import mysql.connector
from mysql.connector import Error
import random
import faker
from datetime import datetime
import uuid

# Tạo một đối tượng Faker để tạo dữ liệu giả
fake = faker.Faker()

def create_connection(host_name, user_name, user_password, db_name):
    """Tạo kết nối đến cơ sở dữ liệu MySQL."""
    connection = None
    try:
        connection = mysql.connector.connect(
            host=host_name,
            user=user_name,
            password=user_password,
            database=db_name
        )
        print("Kết nối thành công đến cơ sở dữ liệu")
    except Error as e:
        print(f"Lỗi '{e}' xảy ra khi kết nối đến cơ sở dữ liệu")
    return connection

def insert_data(connection, uuid_value, username, password, email, name, address, gender, status, dob, image):
    """Chèn dữ liệu vào bảng customers."""
    cursor = connection.cursor()
    query = """
    INSERT INTO customer (uuid, username, password, email, name, address, gender, status, create_at, update_at, dob, image) 
    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
    """
    values = (uuid_value, username, password, email, name, address, gender, status, datetime.now(), datetime.now(), dob, image)
    try:
        cursor.execute(query, values)
        connection.commit()
        print(f"Dữ liệu đã được chèn: {username}, {email}, {address}")
    except Error as e:
        print(f"Lỗi '{e}' xảy ra khi chèn dữ liệu")

def main():
    # Thay đổi thông tin kết nối theo cơ sở dữ liệu của bạn
    connection = create_connection("localhost", "root", "123456", "prodigypm")

    # Tạo và chèn 100 hàng dữ liệu giả
    for _ in range(100000):
        uuid_value = str(uuid.uuid4())  # Tạo UUID
        username = fake.user_name()
        password = fake.password()
        email = fake.email()
        name = fake.name()
        address = fake.address()
        gender = random.choice([True, False])  # Giới tính ngẫu nhiên
        status = random.choice([True, False])  # Trạng thái ngẫu nhiên
        dob = fake.date_of_birth(minimum_age=18, maximum_age=80)  # Ngày sinh từ 18 đến 80 tuổi
        image = None  # Bạn có thể thêm logic để tạo hình ảnh nếu cần

        insert_data(connection, uuid_value, username, password, email, name, address, gender, status, dob, image)

    if connection.is_connected():
        connection.close()
        print("Kết nối đã được đóng")

if __name__ == "__main__":
    main()
