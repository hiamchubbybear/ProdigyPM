import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const ToastService = {
  success: (message, navigate, path = "/home", options = {}) => {
    toast.success(message, {
      position: "top-right",
      autoClose: 1000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "colored",
      onClose: () => {
        navigate(path);
      },
      ...options,
    });
  },

  error: (message, navigate, path = "/error", options = {}) => {
    toast.error(message, {
      position: "top-right",
      autoClose: 3000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "colored",
      onClose: () => {
        navigate(path);
      },
      ...options,
    });
  },

  info: (message, navigate, path = "/info", options = {}) => {
    toast.info(message, {
      position: "top-right",
      autoClose: 3000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "colored",
      onClose: () => {
        navigate(path);
      },
      ...options,
    });
  },

  warn: (message, navigate, path = "/warn", options = {}) => {
    toast.warn(message, {
      position: "top-right",
      autoClose: 3000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "colored",
      onClose: () => {
        navigate(path);
      },
      ...options,
    });
  },
};

export default ToastService;
