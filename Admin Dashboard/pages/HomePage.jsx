import React from 'react';
import Hero from '../components/Hero';
import HomeCards from '../components/HomeCards';
import ExerciseListings from '../components/ExerciseListings';
import ViewAllExercises from '../components/ViewAllExercises';

const HomePage = () => {
  return (
    <>
      <Hero />
      <HomeCards />
      <ExerciseListings isHome={true} />
      <ViewAllExercises />
    </>
  );
}

export default HomePage;
