import { Button, Container, Flex, Text } from '@mantine/core';
import { Link } from 'react-router-dom';

const Home = () => {
  return (
    <Container>
      <Flex
        justify="center"
        align="center"
        direction="column"
        gap="md"
        style={{ height: '100vh', textAlign: 'center' }}
      >
        <Text component="h1" size="xl">
          A{' '}
          <Text component="span" c="blue" size="xl" fw="bolder">
            minimalist
          </Text>{' '}
          productivity app written in Java and React
        </Text>
        <Button component={Link} to="/login">
          Get Started
        </Button>
      </Flex>
    </Container>
  );
};

export default Home;
