import axios from 'axios'

const api = axios.create({
    baseURL: import.meta.env.example.VITE_API_URL //colocar url da api
})

export default api