import asyncio
import discord
import os
import requests
from dotenv import load_dotenv
from fastapi import FastAPI
from pydantic import BaseModel

load_dotenv()  # load all the variables from the env file
bot = discord.Bot(intents=discord.Intents.all())
app = FastAPI()


class JSON_Message(BaseModel):
    username: str
    message: str


@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.post("/message")
async def message_receive(json: JSON_Message):
    channel = bot.get_channel(1219942346485928038)  # Replace with your channel ID
    if not channel:
        return {"error": "Channel not found"}

    await channel.send(f"(Minecraft) [{json.username}]: {json.message}")
    return {"success": True}



@bot.event
async def on_ready():
    print(f"{bot.user} is ready and online!")


@bot.slash_command(name="hello", description="Say hello to the bot")
async def hello(ctx):
    await ctx.respond("Hey!")


@bot.listen()
async def on_message(message: discord.Message):
    if message.author == bot.user or message.channel.id != 1219942346485928038:
        return

    discord_nick = message.author.name
    discord_message = message.content
    print(f"Received {discord_nick}: {discord_message}")

    data = {
        "username": discord_nick,
        "message": discord_message
    }

    try:
        response = requests.post("http://192.168.0.77:4567/message", json=data)
        response.raise_for_status()
        print("Wysłany")
        print(data)
    except Exception as e:
        print("Błąd", e)


async def run():
    try:
        await bot.start(os.getenv("TOKEN"))
    except KeyboardInterrupt:
        await bot.close()


asyncio.create_task(run())
