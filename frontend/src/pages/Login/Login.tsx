import { useState } from 'react';
import { Link, Navigate, useLocation } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
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

import { AppDispatch, RootState } from 'state/store';
import { loginAsync } from 'state/user/authSlice';
import { LoginFormData } from 'types/state/user/authSlice';
import loginShowcase from 'assets/login_showcase.svg';

const Login = () => {
  const isMobile = useMediaQuery(`(max-width: ${em(750)})`);
  const dispatch = useDispatch<AppDispatch>();
  const isAuthenticated = useSelector(
    (state: RootState) => state.auth.isAuthenticated
  );
  const location = useLocation();
  const state = location.state as { next: string };
  const [loginData, setLoginData] = useState<LoginFormData>({
    username: '',
    password: '',
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setLoginData((prevLoginData) => ({
      ...prevLoginData,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e: React.SyntheticEvent) => {
    e.preventDefault();
    console.log(loginData);
    await dispatch(loginAsync(loginData));
  };

  if (isAuthenticated) {
    if (location.state) {
      return <Navigate to={state.next} />;
    } else {
      return <Navigate to="/dashboard" />;
    }
  }

  return (
    <Container>
      <Flex
        justify={isMobile ? 'center' : 'space-around'}
        align="center"
        direction={isMobile ? 'column' : 'row'}
        gap="xl"
        style={{ height: '100vh' }}
      >
        {!isMobile && (
          <div style={{ flex: 1 }}>
            <Image h={200} w="auto" fit="cover" src={loginShowcase} />
          </div>
        )}

        <div style={{ flex: 1 }}>
          <h1 style={{ textAlign: 'center' }}>Login</h1>
          <form onSubmit={handleSubmit}>
            <Stack gap="md">
              <TextInput
                label="Username"
                placeholder="eg: johndoe"
                id="username"
                name="username"
                onChange={handleChange}
              />
              <PasswordInput
                label="Password"
                placeholder="*********"
                id="password"
                name="password"
                onChange={handleChange}
              />
              <Anchor component={Link} size="xs" to="/login">
                Forgot password?
              </Anchor>
              <Button type="submit">Login</Button>
              <Anchor
                component={Link}
                size="xs"
                to="/signup"
                style={{ display: 'block', textAlign: 'right' }}
              >
                Don't have an account? Sign up now
              </Anchor>
            </Stack>
          </form>
        </div>
      </Flex>
    </Container>
  );
};

export default Login;
