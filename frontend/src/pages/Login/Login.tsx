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

const Login = () => {
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
          <h1 style={{ textAlign: 'center' }}>Login</h1>
          <Stack gap="md">
            <TextInput label="Username" placeholder="eg: johndoe" />
            <PasswordInput label="Password" placeholder="*********" />
            <Anchor component={Link} size="xs" to="/login">
              Forgot password?
            </Anchor>
            <Button>Login</Button>
          </Stack>
        </div>
      </Flex>
    </Container>
  );
};

export default Login;
