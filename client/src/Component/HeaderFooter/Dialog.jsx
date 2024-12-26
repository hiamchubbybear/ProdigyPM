import React, { useState } from 'react';
import Modal from 'react-modal';

const Customer = () => {
    Modal.setAppElement('#root');
    const [isOpen, setIsOpen] = useState(false);

    const openModal = () => setIsOpen(true);
    const closeModal = () => setIsOpen(false);

    return (
        <div>
            <button onClick={openModal}>Mở Hộp thoại</button>
            <Modal isOpen={isOpen} onRequestClose={closeModal}>
                <h2>Đây là một Hộp thoại</h2>
                <p>Bạn có thể tùy chỉnh nội dung trong hộp thoại này.</p>
                <button onClick={closeModal}>Đóng</button>
            </Modal>
        </div>
    );
};

export default Customer;
