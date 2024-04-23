import React, { useEffect } from 'react';
import useNasaStore from '../stores/nasaStore';

const Body = () => {
  const { images, error, fetchImages } = useNasaStore();

  useEffect(() => {
    fetchImages(); // Fetch data when component mounts
  }, [fetchImages]); // Include fetchImages in dependencies

  return (
    <div>
      <h1>NASA APOD</h1>
      {images.map((image, index) => (
        <div key={index}>
          <img src={image.url} alt={image.title} />
          <p>{image.title}</p>
          <p>Date: {image.date}</p>
          <p>{image.explanation}</p>
        </div>
      ))}
      {error && <p>Error: {error}</p>}
    </div>
  );
};

export default Body;
