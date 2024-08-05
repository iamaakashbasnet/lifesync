import { Center, Flex } from '@mantine/core';

import logo from './assets/logo.png';

function App() {
  return (
    <Flex justify="center" align="center" style={{ height: '100vh' }}>
      <Center>
        <Flex direction="column" align="center">
          <img width={200} src={logo} alt="logo" />
          <h1>LifeSync</h1>
        </Flex>
      </Center>
    </Flex>
  );
}

export default App;
