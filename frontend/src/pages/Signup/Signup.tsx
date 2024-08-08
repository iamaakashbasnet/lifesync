import {
  Anchor,
  Button,
  Container,
  Flex,
  Image,
  PasswordInput,
  Stack,
  TextInput,
  em,
} from '@mantine/core';
import { useMediaQuery } from '@mantine/hooks';

import loginShowcase from 'assets/login_showcase.svg';
import { Link } from 'react-router-dom';

const Signup = () => {
  const isMobile = useMediaQuery(`(max-width: ${em(750)})`);

  return (
    <Container>
      <Flex
        justify="space-around"
        align="center"
        direction={isMobile ? 'column' : 'row'}
        gap="xl"
        style={{ height: '100vh' }}
      >
        <div style={{ flex: 1 }}>
          <Image h={200} w="auto" fit="cover" src={loginShowcase} />
        </div>

        <div style={{ flex: 1 }}>
          <h1 style={{ textAlign: 'center' }}>Signup</h1>
          <Stack gap="md">
            <TextInput label="Username" placeholder="eg: johndoe" />
            <TextInput label="Email" placeholder="eg: example@exmaple.com" />
            <PasswordInput label="Password" placeholder="*********" />
            <PasswordInput label="Confirm Password" placeholder="*********" />
            <Button>Signup</Button>
            <Anchor
              component={Link}
              size="xs"
              to="/login"
              style={{ display: 'block', textAlign: 'right' }}
            >
              Already have an account? Login now
            </Anchor>
          </Stack>
        </div>
      </Flex>
    </Container>
  );
};

export default Signup;
