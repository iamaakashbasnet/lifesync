import { useState } from 'react';
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
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { useMediaQuery } from '@mantine/hooks';
import { useDispatch, useSelector } from 'react-redux';

import loginShowcase from 'assets/login_showcase.svg';
import { AppDispatch, RootState } from 'state/store';
import { signupAsync } from 'state/user/authSlice';
import { SignupFormData } from 'types/state/user/authSlice';

const Signup = () => {
  const isMobile = useMediaQuery(`(max-width: ${em(750)})`);
  const dispatch = useDispatch<AppDispatch>();
  const isAuthenticated = useSelector(
    (state: RootState) => state.auth.isAuthenticated
  );
  const navigate = useNavigate();
  const [signupData, setSignupData] = useState<SignupFormData>({
    username: '',
    email: '',
    password: '',
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSignupData((prevSignupData) => ({
      ...prevSignupData,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e: React.SyntheticEvent) => {
    e.preventDefault();
    // Need to find better approach than this
    const { type } = await dispatch(signupAsync(signupData));
    if (type != 'auth/signupAsync/rejected') {
      navigate('/login');
    }
  };

  if (isAuthenticated) {
    return <Navigate to="/dashboard" />;
  }

  return (
    <Container>
      <Flex
        justify="space-around"
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
          <h1 style={{ textAlign: 'center' }}>Signup</h1>
          <form onSubmit={handleSubmit}>
            <Stack gap="md">
              <TextInput
                label="Username"
                name="username"
                id="username"
                onChange={handleChange}
                placeholder="eg: johndoe"
              />
              <TextInput
                label="Email"
                name="email"
                id="email"
                onChange={handleChange}
                placeholder="eg: example@exmaple.com"
              />
              <PasswordInput
                label="Password"
                name="password"
                id="password"
                onChange={handleChange}
                placeholder="*********"
              />
              <Button type="submit">Signup</Button>
              <Anchor
                component={Link}
                size="xs"
                to="/login"
                style={{ display: 'block', textAlign: 'right' }}
              >
                Already have an account? Login now
              </Anchor>
            </Stack>
          </form>
        </div>
      </Flex>
    </Container>
  );
};

export default Signup;
