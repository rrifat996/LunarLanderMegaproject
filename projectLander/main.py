import utils
import time
import numpy as np
import tensorflow as tf
from py4j.java_gateway import JavaGateway
from tensorflow.python.keras.losses import MSE
from collections import namedtuple, deque
from keras import Sequential
from keras.layers import Dense, Input
from keras.losses import MSE
from keras.optimizers import Adam
import utils
import random


q_network = None
target_q_network = None


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print('PyCharm')



MEMORY_SIZE = 100_000     # size of memory buffer.
GAMMA = 0.995             # discount factor.
ALPHA = 1e-3              # learning rate.
NUM_STEPS_FOR_UPDATE = 2  # perform a learning update every C time steps.

#position3 rotation3 v3 w3 contact4
state_size = 16;
num_actions = 16;

# dont forget to modify engine manager#

# constructer call set environment
gateway = JavaGateway()
javaMain = gateway.entry_point

# reset environment
javaMain.reset()
# single time step may be 5 10 multiple idk
javaMain.step(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
time.sleep(1 / 6)
listInfo = javaMain.getLatestInfo()
next_state = listInfo[0 : 16]
reward = listInfo[16]
done = listInfo[17]

# utils.display_table(initial_state, action, next_state, reward, done)

# shape will be determined
# Create the Q-Network.
q_network = Sequential([
    ### START CODE HERE ###
    Input(shape=state_size),
    Dense(units=64, activation='relu'),
    Dense(units=64, activation='relu'),
    Dense(units=num_actions, activation='sigmoid'),
    ### END CODE HERE ###
    ])

# Create the target Q^-Network.
target_q_network = Sequential([
    ### START CODE HERE ###
    Input(shape=state_size),
    Dense(units=64, activation='relu'),
    Dense(units=64, activation='relu'),
    Dense(units=num_actions, activation='sigmoid'),
    ### END CODE HERE ###
    ])

### START CODE HERE ###
optimizer = Adam(learning_rate=ALPHA)
### END CODE HERE ###

# Store experiences as named tuples.
experience = namedtuple("Experience", field_names=["state", "actions", "reward", "next_state", "done"])


#total loss for a minibatch
def compute_loss(experiences, gamma, q_network, target_q_network):

    # Unpack the mini-batch of experience tuples.
    states, actions, rewards, next_states, done_vals = experiences

    # Compute max Q^(s,a).
    max_qsa = tf.reduce_max(target_q_network(next_states), axis=-1)

    # Set y = R if episode terminates, otherwise set y = R + ?? max Q^(s,a).
    y_targets = rewards + (gamma * max_qsa * (1 - done_vals))

    # Get the q_values.
    q_values = q_network(states)

    q_values = tf.gather_nd(q_values, tf.stack([tf.range(q_values.shape[0]),
                                                tf.cast(actions, tf.int32)], axis=1))

    # Calculate the loss.
    loss = MSE(y_targets, q_values)

    return loss

def agent_learn(experiences, gamma):
    # Calculate the loss.
    with tf.GradientTape() as tape:
        loss = compute_loss(experiences, gamma, q_network, target_q_network)

    # Get the gradients of the loss with respect to the weights.
    gradients = tape.gradient(loss, q_network.trainable_variables)

    # Update the weights of the q_network.
    optimizer.apply_gradients(zip(gradients, q_network.trainable_variables))

    # update the weights of target q_network.
    utils.update_target_network(q_network, target_q_network)


start = time.time()

num_episodes = 2000
max_num_timesteps = 1000

total_point_history = []

num_p_av = 100  # number of total points to use for averaging.
epsilon = 1.0  # initial ?? value for ??-greedy policy.

# Create a memory buffer D with capacity N.
memory_buffer = deque(maxlen=MEMORY_SIZE)

# Set the target network weights equal to the Q-Network weights.
target_q_network.set_weights(q_network.get_weights())



for i in range(num_episodes):

    # Reset the environment to the initial state and get the initial state.
    zeroList = [20.0, 40.0, 20.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]
    javaMain.reset()
    state = zeroList
    total_points = 0

    for t in range(max_num_timesteps):

        # From the current state S choose an action A using an ??-greedy policy.
        state_qn = np.expand_dims(state, axis=0)  # state needs to be the right shape for the q_network.
        q_values = q_network(state_qn)
        action = utils.get_action(q_values, epsilon)
        print(action)

        # Take action A and receive reward R and the next state S'.

        javaMain.step(action[0],
                      action[1],
                      action[2],
                      action[3],
                      action[4],
                      action[5],
                      action[6],
                      action[7],
                      action[8],
                      action[9],
                      action[10],
                      action[11],
                      action[12],
                      action[13],
                      action[14],
                      action[15])
        time.sleep(1 / 16)
        list2 = javaMain.getLatestInfo()
        print(list2)
        next_state = list(list2[0: 16])
        reward = list2[16]
        done = list2[17]

        #next_state, reward, done, _ = javaMain.step(action)

        # Store experience tuple (S,A,R,S') in the memory buffer.
        # We store the done variable as well for convenience.

        intAction = 0
        intAction += action[0] * 1
        intAction += action[1] * 2
        intAction += action[2] * 2 * 2
        intAction += action[3] * 2 * 2 * 2
        intAction += action[4] * 2 * 2 * 2 * 2
        intAction += action[5] * 2 * 2 * 2 * 2 * 2
        intAction += action[6] * 2 * 2 * 2 * 2 * 2 * 2
        intAction += action[7] * 2 * 2 * 2 * 2 * 2 * 2 * 2
        intAction += action[8] * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2
        intAction += action[9] * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2
        intAction += action[10] * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2
        intAction += action[11] * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2
        intAction += action[12] * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2
        intAction += action[13] * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2
        intAction += action[14] * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2
        intAction += action[15] * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2



        memory_buffer.append(experience(state, intAction % 256 , reward, next_state, done))

        # Only update the network every NUM_STEPS_FOR_UPDATE time steps.
        update = utils.check_update_conditions(t, NUM_STEPS_FOR_UPDATE, memory_buffer)

        if update:
            # Sample random mini-batch of experience tuples (S,A,R,S') from D.
            experiences = utils.get_experiences(memory_buffer)
            # Set the y targets, perform a gradient descent step,
            # and update the network weights.
            agent_learn(experiences, GAMMA)

        state = next_state.copy()
        total_points += reward

        if done > 0.5:
            break

    total_point_history.append(total_points)
    av_latest_points = np.mean(total_point_history[-num_p_av:])

    # Update the ?? value.
    epsilon = utils.get_new_eps(epsilon)

    print(f"\rEpisode {i + 1} | Total point average of the last {num_p_av} episodes: {av_latest_points:.2f}", end="")

    if (i + 1) % num_p_av == 0:
        print(f"\rEpisode {i + 1} | Total point average of the last {num_p_av} episodes: {av_latest_points:.2f}")

    # We will consider that the environment is solved if we get an
    # average of 200 points in the last 100 episodes.
    if av_latest_points >= 1000.0:
        print(f"\n\nEnvironment solved in {i + 1} episodes!")
        q_network.save('lunar_lander_model.h5')
        break

tot_time = time.time() - start

print(f"\nTotal Runtime: {tot_time:.2f} s ({(tot_time / 60):.2f} min)")