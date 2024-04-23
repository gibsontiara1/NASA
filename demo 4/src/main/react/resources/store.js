// nasaStore.js
import create from 'zustand';

const useNasaStore = create((set) => ({
  images: [],
  error: null,
  fetchImages: async () => {
    try {
      const response = await fetch('/api/nasa/apod');
      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }
      const data = await response.json();
      set({ images: data, error: null });
    } catch (error) {
      set({ error: error.message });
    }
  },
}));

export default useNasaStore;
